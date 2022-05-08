package io.nagurea.smsupsdk.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class E164FormatException extends RuntimeException {

    private final String phoneNumber;

    public static E164FormatException newE164EncodingException(String phoneNumber){
        return new E164FormatException(phoneNumber);
    }

    @Override
    public String getMessage() {
        return phoneNumber + " does not follow the E164 pattern";
    }
}
