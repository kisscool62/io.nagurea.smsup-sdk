package io.nagurea.smsupsdk.invoices.download;


import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.common.response.PDFDocument;
import io.nagurea.smsupsdk.helper.URLHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class DownloadInvoiceService extends GETSMSUpService {

    private static final String URL = "/account/invoice";

    public DownloadInvoiceService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Download invoice from invoice id
     * @param token SMSUp token
     * @param id The invoice id
     * @return DownloadInvoiceResponse with detailed @{@link OutputStream}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public DownloadInvoiceResponse downloadInvoice(String token, String id) throws IOException {
        final ImmutablePair<Integer, PDFDocument> response = getPDF(buildUrlById(id), token);
        final PDFDocument pdf = response.getRight();
        return DownloadInvoiceResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(pdf)
                .build();
    }

    private String buildUrlById(String id) {
        return URLHelper.add(URLHelper.add(URL, id), "download");
    }
}
