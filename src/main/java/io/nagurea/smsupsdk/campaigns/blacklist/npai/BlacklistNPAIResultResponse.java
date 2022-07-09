package io.nagurea.smsupsdk.campaigns.blacklist.npai;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class BlacklistNPAIResultResponse extends ResultResponse {

    @SerializedName("NPAI")
    private final Integer npai;

    @Builder
    public BlacklistNPAIResultResponse(
            ResponseStatus responseStatus,
            String message, Integer npai) {
        super(responseStatus, message);
        this.npai = npai;
    }

}
