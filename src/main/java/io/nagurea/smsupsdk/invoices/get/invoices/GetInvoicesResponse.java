package io.nagurea.smsupsdk.invoices.get.invoices;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetInvoicesResponse extends APIResponse<GetInvoicesResultResponse> {

    @Builder
    public GetInvoicesResponse(String uid, Integer statusCode, String additionalMessage, GetInvoicesResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
