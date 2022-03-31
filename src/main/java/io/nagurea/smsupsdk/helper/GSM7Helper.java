package io.nagurea.smsupsdk.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GSM7Helper {
    private static final String ALLOWED_CHARACTERS = "@£$¥èéùìòÇØøÅåΔ_ΦΓΛΩΠΨΣΘΞ^{}\\[~]|€ÆæßÉ!\"#¤%&'()*+,-./0123456789:;<=>?¡ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÑÜ§¿abcdefghijklmnopqrstuvwxyzäöñüà";

    public static GSM7EncodingErrors checkErrors(final String text){
        GSM7EncodingErrors errors = new GSM7EncodingErrors();
        return errors;
    }

}
