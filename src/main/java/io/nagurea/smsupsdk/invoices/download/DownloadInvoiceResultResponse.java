package io.nagurea.smsupsdk.invoices.download;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.invoices.get.Invoice;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class DownloadInvoiceResultResponse extends ResultResponse {

    private final Invoice invoice;

    @Builder
    public DownloadInvoiceResultResponse(
            ResponseStatus responseStatus,
            String message, Invoice invoice) {
        super(responseStatus, message);
        this.invoice = invoice;
    }

}
