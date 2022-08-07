package io.nagurea.smsupsdk.invoices.get.download;

import io.nagurea.smsupsdk.common.response.APIOutputStreamResponse;
import io.nagurea.smsupsdk.common.response.PDFDocument;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DownloadInvoiceResponse extends APIOutputStreamResponse<PDFDocument> {

    @Builder
    public DownloadInvoiceResponse(String uid, Integer statusCode, String additionalMessage, PDFDocument effectiveResponse) {
        super(uid, statusCode, additionalMessage, effectiveResponse);
    }

}
