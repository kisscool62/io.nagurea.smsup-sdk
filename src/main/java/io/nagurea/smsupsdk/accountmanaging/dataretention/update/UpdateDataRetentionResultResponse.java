package io.nagurea.smsupsdk.accountmanaging.dataretention.update;

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
public class UpdateDataRetentionResultResponse extends ResultResponse {

    private final DataRetentionResult retention;

    @Builder
    public UpdateDataRetentionResultResponse(ResponseStatus responseStatus, String message, DataRetentionResult retention) {
        super(responseStatus, message);
        this.retention = retention;
    }


}
