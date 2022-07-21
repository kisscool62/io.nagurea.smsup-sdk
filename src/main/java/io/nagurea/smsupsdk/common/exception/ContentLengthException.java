package io.nagurea.smsupsdk.common.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentLengthException extends RuntimeException{

    private ContentLengthException(int expectedLength, int actualLength){
        super(String.format("content-length expected with %s but had %s", expectedLength, actualLength));
    }

    public static ContentLengthException newContentLengthException(int expectedLength, int actualLength){
        return new ContentLengthException(expectedLength, actualLength);
    }
}
