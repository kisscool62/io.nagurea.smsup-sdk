package io.nagurea.smsupsdk.common.post;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;

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

    public String post(String postUrl, String token, String data) throws IOException {
        URL url = new URL(getRootUrl() + postUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);

        this.sendData(con, data);

        return this.read(con.getInputStream());
    }


}
