package io.nagurea.smsupsdk.invoices.get.invoices;


import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class GetInvoicesService extends GETSMSUpService {

    private static final String URL = "/account/invoice";

    public GetInvoicesService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Get invoices
     * @param token SMSUp token
     * @return GetInvoicesResponse with detailed @GetInvoicesResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetInvoicesResponse getInvoices(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final GetInvoicesResultResponse responseObject = GsonHelper.fromJson(body, GetInvoicesResultResponse.class);
        return GetInvoicesResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
