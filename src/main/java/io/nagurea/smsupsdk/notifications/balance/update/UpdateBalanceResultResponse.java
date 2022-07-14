package io.nagurea.smsupsdk.notifications.balance.update;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.notifications.balance.update.body.response.Notification;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class UpdateBalanceResultResponse extends ResultResponse {

    private final Notification notification;

    @Builder
    public UpdateBalanceResultResponse(ResponseStatus responseStatus, String message, Notification notification) {
        super(responseStatus, message);
        this.notification = notification;
    }


}
