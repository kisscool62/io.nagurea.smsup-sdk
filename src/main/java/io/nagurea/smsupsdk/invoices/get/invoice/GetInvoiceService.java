package io.nagurea.smsupsdk.invoices.get.invoice;


import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.URLHelper;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetInvoiceService extends GETSMSUpService {

    private static final String URL = "/account/invoice";

    protected GetInvoiceService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Get invoice from invoice id
     * @param token SMSUp token
     * @param id The invoice id
     * @return GetInvoiceResponse with detailed @GetInvoiceResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetInvoiceResponse getInvoice(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildUrlById(id), token);
        final String body = response.getRight();
        final GetInvoiceResultResponse responseObject = GsonHelper.fromJson(body, GetInvoiceResultResponse.class);
        return GetInvoiceResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrlById(String id) {
        return URLHelper.add(URL, id);
    }
}
