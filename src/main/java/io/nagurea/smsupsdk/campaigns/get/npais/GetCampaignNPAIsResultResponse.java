package io.nagurea.smsupsdk.campaigns.get.npais;

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
public class GetCampaignNPAIsResultResponse extends ResultResponse {

    private final List<NPAI> npais;

    @Builder
    public GetCampaignNPAIsResultResponse(
            ResponseStatus responseStatus,
            String message, List<NPAI> npais) {
        super(responseStatus, message);
        this.npais = npais;
    }

}
