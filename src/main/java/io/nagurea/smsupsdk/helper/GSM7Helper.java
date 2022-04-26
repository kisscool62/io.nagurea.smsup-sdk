package io.nagurea.smsupsdk.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GSM7Helper {
    private static final String ALLOWED_CHARACTERS = "@£$¥èéùìòÇØøÅåΔ_ΦΓΛΩΠΨΣΘΞ^{}\\[~]|€ÆæßÉ!\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";

    /**
     * Check the text for GSM 7 character encoding and returns characters not following the GSM7 encoding
     * This method could be costly. Use only if needed.
     * @param text is the text to check
     * @return @{@link GSM7EncodingErrors} which is a list of characters in error
     */
    public static GSM7EncodingErrors checkAndFindErrors(final String text) {
        GSM7EncodingErrors errors = new GSM7EncodingErrors();
        final char[] textChars = text.toCharArray();
        for (char textChar : textChars) {
            final String character = String.valueOf(textChar);
            if (!ALLOWED_CHARACTERS.contains(character)) {
                errors.add(character);
            }
        }
        return errors;
    }

}
