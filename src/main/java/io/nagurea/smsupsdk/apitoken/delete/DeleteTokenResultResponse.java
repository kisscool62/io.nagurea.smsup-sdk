package io.nagurea.smsupsdk.apitoken.delete;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class DeleteTokenResultResponse extends ResultResponse {

    @SerializedName("deleted_token")
    private final String deletedToken; // Ex: "17"

    @Builder
    public DeleteTokenResultResponse(
            ResponseStatus responseStatus,
            String message, String deletedToken) {
        super(responseStatus, message);
        this.deletedToken = deletedToken;
    }

}
