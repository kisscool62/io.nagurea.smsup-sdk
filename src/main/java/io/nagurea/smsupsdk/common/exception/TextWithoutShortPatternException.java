package io.nagurea.smsupsdk.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TextWithoutShortPatternException extends RuntimeException {

    private final String text;
    private final String shortLinkPattern;

    public static TextWithoutShortPatternException newTextWithoutShortPatternException(String text, String shortLinkPattern){
        return new TextWithoutShortPatternException(text, shortLinkPattern);
    }

    @Override
    public String getMessage() {
        return text + " does not contain short link pattern " + shortLinkPattern;
    }
}
