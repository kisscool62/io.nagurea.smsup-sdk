package io.nagurea.smsupsdk.apierrors.common;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Builder
@Getter
public class Headers {

    @SerializedName("Content-Type")
    private final String contentType;

    @SerializedName("Host")
    private final  String host;
}
