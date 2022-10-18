package io.nagurea.smsupsdk.helper;

import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLHelper {

    private static final String SLASH = "/";
    private static final String ID = "id";

    public static URL add(URL rootUrl, String additionalPath) throws MalformedURLException {
        String spec = rootUrl.toString();
        return new URL(add(spec, additionalPath));
    }

    public static String add(String spec, String additionalPath) {
        if(!isTrailedBySlash(spec)) {
            spec += SLASH;
        }

        return spec + additionalPath;
    }

    public static String buildUrl(@NonNull String url, @NonNull String id) {
        final RequiredParameterException.RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format(url, id);
    }

    private static boolean isTrailedBySlash(String spec) {
        return spec != null && spec.endsWith(SLASH);
    }
}
