package io.nagurea.smsupsdk.common;

import io.nagurea.smsupsdk.common.http.HTTPMethod;
import lombok.AllArgsConstructor;
import org.xml.sax.HandlerBase;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
public abstract class SMSUpService {
    protected static final String TEXT = "text";

    private final String rootUrl;

    protected abstract HTTPMethod getHttpMethod();

    protected void sendData(HttpURLConnection con, String data) throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            if(data != null){
                wr.writeBytes(data);
            }
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }

    protected void sendData(HttpURLConnection con) throws IOException {
        sendData(con, null);
    }

    protected String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

            return body.toString();
        } catch(IOException ioe) {
            throw ioe;
        } finally {
            this.closeQuietly(in);
        }
    }

    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }

    public String getRootUrl() {
        return rootUrl;
    }

    protected HttpURLConnection getHttpURLConnectionWithBearer(String token, URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(getHttpMethod().toString());
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization","Bearer " + token);
        con.setDoOutput(true);
        return con;
    }
}
