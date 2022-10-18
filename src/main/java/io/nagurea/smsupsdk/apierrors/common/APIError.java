package io.nagurea.smsupsdk.apierrors.common;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode
@ToString
@Builder
@Getter
public class APIError {

    @SerializedName("log_id")
    private final String logId;

    private final LocalDateTime date;

    private final String method;

    private final String url;

    @SerializedName("error_code")
    private final String errorCode;

    private final String ip;

    private final  Headers headers;

    @SerializedName("query_param")
    private final String queryParam;

    private final String body;
}
