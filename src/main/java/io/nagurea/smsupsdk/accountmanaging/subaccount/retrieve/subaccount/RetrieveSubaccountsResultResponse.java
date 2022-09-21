package io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.subaccount;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class RetrieveSubaccountsResultResponse extends ResultResponse {

    @SerializedName("sub-accounts")
    private final List<SubaccountInfo> subaccounts;

    @Builder
    public RetrieveSubaccountsResultResponse(ResponseStatus responseStatus, String message, @Singular List<SubaccountInfo> subaccounts) {
        super(responseStatus, message);
        this.subaccounts = subaccounts;
    }


}
