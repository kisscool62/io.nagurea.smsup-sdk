package io.nagurea.smsupsdk.apitoken.create;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class CreateTokenResultResponse extends ResultResponse {

    private final String token;

    @SerializedName("token_id")
    private final String tokenId;

    @Builder
    public CreateTokenResultResponse(
            ResponseStatus responseStatus,
            String message, String token, String tokenId) {
        super(responseStatus, message);
        this.token = token;
        this.tokenId = tokenId;
    }

}
