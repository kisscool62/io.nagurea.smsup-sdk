package io.nagurea.smsupsdk.accountmanaging.credits;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class CreditsResultResponse extends ResultResponse {

    private final String credits;
    private final String postpaid;

    @Builder
    public CreditsResultResponse(ResponseStatus responseStatus, String message, String credits, String postpaid) {
        super(responseStatus, message);
        this.credits = credits;
        this.postpaid = postpaid;
    }


}
