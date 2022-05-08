package io.nagurea.smsupsdk.common.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SMSUpURLEncoder {

    public static String encode(String text){
        return URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

}
