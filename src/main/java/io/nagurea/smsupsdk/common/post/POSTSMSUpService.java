package io.nagurea.smsupsdk.common.post;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class POSTSMSUpService extends SMSUpService {
    protected POSTSMSUpService(String rootUrl) {
        super(rootUrl);
    }

    @Override
    protected HTTPMethod getHttpMethod(){
        return HTTPMethod.POST;
    }

    protected ImmutablePair<Integer, String> post(String postUrl, String token, String data) throws IOException {
        URL url = new URL(getRootUrl() + postUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);
        con.setDoOutput(true);

        this.sendData(con, data);

        return new ImmutablePair<>(con.getResponseCode(), this.read(con.getInputStream()));
    }

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



}
