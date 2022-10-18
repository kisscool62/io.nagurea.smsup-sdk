package io.nagurea.smsupsdk.apierrors.apierror;

import io.nagurea.smsupsdk.apierrors.common.APIError;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class GetAPIErrorResultResponse extends ResultResponse {

    private final APIError data;

    @Builder
    public GetAPIErrorResultResponse(ResponseStatus responseStatus, String message, APIError data) {
        super(responseStatus, message);
        this.data = data;
    }


}
