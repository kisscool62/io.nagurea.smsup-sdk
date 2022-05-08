package io.nagurea.smsupsdk.common.e164;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.nagurea.smsupsdk.common.exception.E164FormatException.newE164EncodingException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class E164Helper {

    private static final Pattern E164 = Pattern.compile("^\\+?[1-9]\\d{1,14}$");

    public static void check(String phoneNumber){
        Matcher matcher = E164.matcher(phoneNumber);
        if(!matcher.matches()){
            throw newE164EncodingException(phoneNumber);
        }
    }
}
