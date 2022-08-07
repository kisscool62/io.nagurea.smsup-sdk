package io.nagurea.smsupsdk.helper;

import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.ByteArrayOutputStream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MD5Helper {

    public static String md5sum(ByteArrayOutputStream file) {
        byte[] data = new byte[]{};
        if(file != null) {
            data = file.toByteArray();
        }
        return DigestUtils.md5Hex(data);
    }

}
