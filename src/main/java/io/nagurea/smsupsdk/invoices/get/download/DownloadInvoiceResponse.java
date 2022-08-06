package io.nagurea.smsupsdk.invoices.get.download;

import io.nagurea.smsupsdk.common.response.APIOutputStreamResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.OutputStream;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DownloadInvoiceResponse extends APIOutputStreamResponse<OutputStream> {

    @Builder
    public DownloadInvoiceResponse(String uid, Integer statusCode, String additionalMessage, OutputStream effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
