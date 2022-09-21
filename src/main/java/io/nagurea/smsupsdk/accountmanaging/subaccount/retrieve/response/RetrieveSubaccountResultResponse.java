package io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class RetrieveSubaccountResultResponse extends ResultResponse {

    @SerializedName("sub-account")
    private final SubaccountInfo subaccount;

    @Builder
    public RetrieveSubaccountResultResponse(ResponseStatus responseStatus, String message, SubaccountInfo subaccount) {
        super(responseStatus, message);
        this.subaccount = subaccount;
    }


}
