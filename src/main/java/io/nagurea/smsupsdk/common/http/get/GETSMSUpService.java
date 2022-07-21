package io.nagurea.smsupsdk.common.http.get;

import io.nagurea.smsupsdk.common.SMSUpService;
import io.nagurea.smsupsdk.common.http.HTTPMethod;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class GETSMSUpService extends SMSUpService {
    protected GETSMSUpService(String rootUrl) {
        super(rootUrl);
    }

    @Override
    protected HTTPMethod getHttpMethod(){
        return HTTPMethod.GET;
    }

    protected ImmutablePair<Integer, String> get(String getUrl, String token) throws IOException {
        URL url = new URL(getRootUrl() + getUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);

        return new ImmutablePair<>(con.getResponseCode(), this.read(con.getInputStream()));
    }

    protected ImmutablePair<Integer, OutputStream> getPDF(String getUrl, String token) throws IOException {
        URL url = new URL(getRootUrl() + getUrl);

        HttpURLConnection con = getHttpURLConnectionWithBearer(token, url);
        int responseCode = con.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = con.getHeaderField("Content-Disposition");
            String contentType = con.getContentType();
            if (!"application/pdf".equals(contentType)) {
                throw new NotImplementedException(contentType + " is not yet implemented");
            }
            int contentLength = con.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = "noFilenameFoundInContentDispositionHeader-" + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
            }

            return new ImmutablePair<>(con.getResponseCode(), this.readPDF(con.getInputStream(), contentLength, fileName));
        } else {
            return new ImmutablePair<>(con.getResponseCode(), null);
        }

    }


}
