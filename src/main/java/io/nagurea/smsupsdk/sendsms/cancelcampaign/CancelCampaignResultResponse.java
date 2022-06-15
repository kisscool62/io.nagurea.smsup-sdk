package io.nagurea.smsupsdk.sendsms.cancelcampaign;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
public class CancelCampaignResultResponse extends ResultResponse {

    private final Integer credits;

    @Builder
    public CancelCampaignResultResponse(
            ResponseStatus responseStatus,
            String message, Integer credits) {
        super(responseStatus, message);
        this.credits = credits;
    }

}
