package io.nagurea.smsupsdk.invoices.get.invoices;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.invoices.get.Invoice;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class GetInvoicesResultResponse extends ResultResponse {

    private final List<Invoice> invoices;

    @Builder
    public GetInvoicesResultResponse(
            ResponseStatus responseStatus,
            String message, List<Invoice> invoices) {
        super(responseStatus, message);
        this.invoices = invoices;
    }

}
