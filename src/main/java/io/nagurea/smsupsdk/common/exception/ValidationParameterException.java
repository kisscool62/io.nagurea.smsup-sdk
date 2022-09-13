package io.nagurea.smsupsdk.common.exception;

import lombok.Builder;
import lombok.Singular;

import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

@Builder
public class ValidationParameterException extends RuntimeException{

    @Singular
    private final Map<String, Object> validatedParams;



    @Override
    public String getMessage() {
        return validatedParams.entrySet().stream().map(
               e -> String.format("%s failed validation because of: %s", e.getKey(), e.getValue())
        ).collect(Collectors.joining(lineSeparator()));
    }

}
