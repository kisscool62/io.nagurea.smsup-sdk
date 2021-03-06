package io.nagurea.smsupsdk.campaigns.get.stops;

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
public class GetCampaignStopsResultResponse extends ResultResponse {

    private final List<Stop> stops;

    @Builder
    public GetCampaignStopsResultResponse(
            ResponseStatus responseStatus,
            String message, List<Stop> stops) {
        super(responseStatus, message);
        this.stops = stops;
    }

}
