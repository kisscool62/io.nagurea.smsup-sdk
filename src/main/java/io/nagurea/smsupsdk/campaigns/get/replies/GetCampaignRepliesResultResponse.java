package io.nagurea.smsupsdk.campaigns.get.replies;

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
public class GetCampaignRepliesResultResponse extends ResultResponse {

    private final List<Reply> replies;

    @Builder
    public GetCampaignRepliesResultResponse(
            ResponseStatus responseStatus,
            String message, List<Reply> replies) {
        super(responseStatus, message);
        this.replies = replies;
    }

}
