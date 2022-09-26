package io.nagurea.smsupsdk.accountmanaging.dataretention.get.response;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.accountmanaging.dataretention.DataRetentionResult;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class GetDataRetentionResultResponse extends ResultResponse {

    @SerializedName("data_retention")
    private final DataRetentionResult dataRetention;

    @Builder
    public GetDataRetentionResultResponse(ResponseStatus responseStatus, String message, DataRetentionResult dataRetention) {
        super(responseStatus, message);
        this.dataRetention = dataRetention;
    }


}
