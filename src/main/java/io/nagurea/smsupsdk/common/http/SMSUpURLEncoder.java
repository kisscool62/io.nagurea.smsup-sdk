package io.nagurea.smsupsdk.common.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SMSUpURLEncoder {

    public static String encode(String text){
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding failed");
        }
    }

}
