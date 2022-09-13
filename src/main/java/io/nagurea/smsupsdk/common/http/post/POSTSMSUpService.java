package io.nagurea.smsupsdk.common.http.post;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        this.sendData(con, data);

        return new ImmutablePair<>(con.getResponseCode(), this.read(con.getInputStream()));
    }

    protected void sendData(HttpURLConnection con, String data) throws IOException {
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = data.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }



}
