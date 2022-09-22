package io.nagurea.smsupsdk.accountmanaging.transfercredits;

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
public class TransferCreditsResultResponse extends ResultResponse {

    private final String credits;

    @SerializedName("child_credits")
    private final String childCredits;

    @Builder
    public TransferCreditsResultResponse(
            ResponseStatus responseStatus,
            String message, String credits, String childCredits) {
        super(responseStatus, message);
        this.credits = credits;
        this.childCredits = childCredits;
    }

}
