package io.nagurea.smsupsdk.common.http.delete;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DELETESMSUpService extends SMSUpService {
    protected DELETESMSUpService(String rootUrl) {
        super(rootUrl);
    }

    @Override
    protected HTTPMethod getHttpMethod(){
        return HTTPMethod.DELETE;
    }

    protected ImmutablePair<Integer, String> delete(String getUrl, String token) throws IOException {
        URL url = new URL(getRootUrl() + getUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);

        return new ImmutablePair<>(con.getResponseCode(), this.read(con.getInputStream()));
    }



}
