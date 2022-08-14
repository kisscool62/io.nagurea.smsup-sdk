package io.nagurea.smsupsdk.hlrlookup;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.hlrlookup.response.HLRLookup;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class HLRLookupResultResponse extends ResultResponse {

    private final Integer cost;
    private final Integer credits;
    private final HLRLookup response;

    @Builder
    public HLRLookupResultResponse(ResponseStatus responseStatus, String message, Integer cost, Integer credits, HLRLookup response) {
        super(responseStatus, message);
        this.cost = cost;
        this.credits = credits;
        this.response = response;
    }


}
