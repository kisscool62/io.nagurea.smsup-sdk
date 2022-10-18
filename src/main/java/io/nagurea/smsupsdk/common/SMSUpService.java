package io.nagurea.smsupsdk.common;

import io.nagurea.smsupsdk.common.http.HTTPMethod;
import io.nagurea.smsupsdk.common.response.PDFDocument;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.nagurea.smsupsdk.common.exception.ContentLengthException.newContentLengthException;
import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
public abstract class SMSUpService {
    protected static final String TEXT = "text";

    private final String rootUrl;

    protected abstract HTTPMethod getHttpMethod();

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

    protected PDFDocument readPDF(InputStream is, int contentLength, String fileName) throws IOException {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            final byte[] bytes = IOUtils.toByteArray(is);
            if(contentLength != bytes.length){
                throw newContentLengthException(contentLength, bytes.length);
            }
            out.write(bytes);

            return PDFDocument.builder()
                    .filename(fileName)
                    .documentOutputStream(out)
                    .build();
        }

    }

    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ignored) {

        }
    }

    public String getRootUrl() {
        return rootUrl;
    }

    protected HttpURLConnection getHttpURLConnectionWithBearer(String token, URL url) throws IOException {
        HttpURLConnection con = initiateConnection(token, url);
        con.setRequestProperty("Accept", "application/json");
        return con;
    }

    protected HttpURLConnection getPDFFileConnectionWithBearer(String token, URL url) throws IOException {
        HttpURLConnection con = initiateConnection(token, url);
        con.setRequestProperty("Accept", "application/pdf");
        return con;
    }

    private HttpURLConnection initiateConnection(String token, URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(getHttpMethod().toString());
        con.setRequestProperty("Authorization", "Bearer " + token);
        return con;
    }
}
