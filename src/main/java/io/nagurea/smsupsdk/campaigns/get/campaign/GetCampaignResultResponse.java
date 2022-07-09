package io.nagurea.smsupsdk.campaigns.get.campaign;

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
public class GetCampaignResultResponse extends ResultResponse {

    private final List<Campaign> campaign;
    private final Integer totalRecords;
    private final Integer totalDisplayRecords;

    @Builder
    public GetCampaignResultResponse(
            ResponseStatus responseStatus,
            String message, List<Campaign> campaign, Integer totalRecords, Integer totalDisplayRecords) {
        super(responseStatus, message);
        this.campaign = campaign;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalDisplayRecords;
    }

}
