package io.nagurea.smsupsdk.notifications.balance.get;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class GetNotificationResultResponse extends ResultResponse {

    private final Notification notification;

    @Builder
    public GetNotificationResultResponse(
            ResponseStatus responseStatus,
            String message, Notification notification) {
        super(responseStatus, message);
        this.notification = notification;
    }

}
