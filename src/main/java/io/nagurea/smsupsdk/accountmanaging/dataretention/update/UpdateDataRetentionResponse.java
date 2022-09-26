package io.nagurea.smsupsdk.accountmanaging.dataretention.update;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class UpdateDataRetentionResponse extends APIResponse<UpdateDataRetentionResultResponse> {

    @Builder
    public UpdateDataRetentionResponse(String uid, Integer statusCode, String additionalMessage, UpdateDataRetentionResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }
}
