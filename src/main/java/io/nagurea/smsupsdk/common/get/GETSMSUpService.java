package io.nagurea.smsupsdk.common.get;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GETSMSUpService extends SMSUpService {
    protected GETSMSUpService(String rootUrl) {
        super(rootUrl);
    }

    @Override
    protected HTTPMethod getHttpMethod(){
        return HTTPMethod.GET;
    }

    public String get(String getUrl, String token) throws IOException {
        URL url = new URL(getRootUrl() + getUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);

        this.sendData(con);

        return this.read(con.getInputStream());
    }


}
