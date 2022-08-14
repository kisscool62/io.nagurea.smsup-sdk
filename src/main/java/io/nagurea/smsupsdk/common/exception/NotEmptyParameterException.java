package io.nagurea.smsupsdk.common.exception;

import lombok.Builder;
import lombok.Singular;

import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

@Builder
public class NotEmptyParameterException extends RuntimeException{

    @Singular
    private final Map<String, Object> requiredParams;



    @Override
    public String getMessage() {
        return requiredParams.entrySet().stream().map(
               e -> String.format("%s is required to ne not empty", e.getKey())
        ).collect(Collectors.joining(lineSeparator()));
    }

}
