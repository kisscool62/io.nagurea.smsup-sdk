package io.nagurea.smsupsdk.common.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.OutputStream;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class APIOutputStreamResponse<T extends OutputStream>{

    private final String uid;
    private final Integer statusCode;
    private final String additionalMessage;
    private final T effectiveResponse;

}
