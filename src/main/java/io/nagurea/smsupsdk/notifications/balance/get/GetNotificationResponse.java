package io.nagurea.smsupsdk.notifications.balance.get;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetNotificationResponse extends APIResponse<GetNotificationResultResponse> {

    @Builder
    public GetNotificationResponse(String uid, Integer statusCode, String additionalMessage, GetNotificationResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
