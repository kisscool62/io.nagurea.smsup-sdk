package io.nagurea.smsupsdk.common.http.put;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PUTSMSUpService extends SMSUpService {
    protected PUTSMSUpService(String rootUrl) {
        super(rootUrl);
    }

    @Override
    protected HTTPMethod getHttpMethod(){
        return HTTPMethod.PUT;
    }

    protected ImmutablePair<Integer, String> put(String postUrl, String token) throws IOException {
        URL url = new URL(getRootUrl() + postUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);
        con.setDoOutput(true);

        return new ImmutablePair<>(con.getResponseCode(), this.read(con.getInputStream()));
    }

    protected ImmutablePair<Integer, String> put(String postUrl, String token, @NonNull String data) throws IOException {
        URL url = new URL(getRootUrl() + postUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);
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
