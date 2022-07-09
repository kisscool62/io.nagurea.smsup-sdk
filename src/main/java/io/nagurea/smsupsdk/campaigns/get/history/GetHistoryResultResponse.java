package io.nagurea.smsupsdk.campaigns.get.history;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class GetHistoryResultResponse extends ResultResponse {

    private final List<Campaign> campaigns;

    @Builder
    public GetHistoryResultResponse(
            ResponseStatus responseStatus,
            String message, List<Campaign> campaigns) {
        super(responseStatus, message);
        this.campaigns = campaigns;
    }

}
