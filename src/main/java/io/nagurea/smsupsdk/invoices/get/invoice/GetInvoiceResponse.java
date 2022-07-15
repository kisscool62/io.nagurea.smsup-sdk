package io.nagurea.smsupsdk.invoices.get.invoice;

import io.nagurea.smsupsdk.common.response.APIResponse;
import lombok.Builder;
import lombok.ToString;

@ToString
public class GetInvoiceResponse extends APIResponse<GetInvoiceResultResponse> {

    @Builder
    public GetInvoiceResponse(String uid, Integer statusCode, String additionalMessage, GetInvoiceResultResponse effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
