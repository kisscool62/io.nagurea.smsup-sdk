package io.nagurea.smsupsdk.webhooks.delete.response;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class DeleteWebhookResultResponse extends ResultResponse {

    @SerializedName("deleted_id")
    private final String deletedId; // Ex: "66"

    @Builder
    public DeleteWebhookResultResponse(
            ResponseStatus responseStatus,
            String message, String deletedId) {
        super(responseStatus, message);
        this.deletedId = deletedId;
    }

}
