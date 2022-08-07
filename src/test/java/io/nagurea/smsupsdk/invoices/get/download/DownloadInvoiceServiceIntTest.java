package io.nagurea.smsupsdk.invoices.get.download;

import io.nagurea.smsupsdk.common.response.PDFDocument;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class DownloadInvoiceServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String INVOICE_ID = "778";
    private static final String PDF_FILENAME = "invoice-document.pdf";

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private DownloadInvoiceService downloadInvoiceService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer() throws IOException {
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/account/invoice/" + INVOICE_ID + "/download")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200).withContentType(MediaType.PDF)
                        .withHeader("Content-Disposition", "attachment", "filename=\"" + PDF_FILENAME + "\"")
                        .withBody(getPdfFileAsBytes())
        );
    }

    private static byte[] getPdfFileAsBytes() throws IOException {
        return DownloadInvoiceServiceIntTest.class.getClassLoader().getResourceAsStream("io/nagurea/smsupsdk/invoices/get/download/test_download.pdf").readAllBytes();
    }

    @AfterAll
    static void stopMockserver() {
        mockServer.stop();
    }

    @Test
    void downloadInvoice() throws IOException {
        //given
        byte[] bytes = getPdfFileAsBytes();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
        byteArrayOutputStream.write(bytes);

        final DownloadInvoiceResponse expectedResponse = DownloadInvoiceResponse.builder()
                .effectiveResponse(
                        PDFDocument.builder()
                                .filename(PDF_FILENAME)
                                .documentOutputStream(byteArrayOutputStream)
                                .build()
                        ).build();
        final int expectedStatusCode = 200;

        //when
        final DownloadInvoiceResponse result = downloadInvoiceService.downloadInvoice(YOUR_TOKEN, INVOICE_ID);
        final Integer effectiveStatusCode = result.getStatusCode();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        final PDFDocument expectedPDFDocument = expectedResponse.getEffectiveResponse();
        final PDFDocument actualPDFDocument = result.getEffectiveResponse();

        assertEquals(expectedPDFDocument, actualPDFDocument);

    }

}
