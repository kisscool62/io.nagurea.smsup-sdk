package io.nagurea.smsupsdk.invoices.get.invoices;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.invoices.get.Invoice;
import io.nagurea.smsupsdk.invoices.get.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class GetInvoicesServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetInvoicesService invoicesService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/account/invoice")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"invoices\": [\n" +
                                        "      {\n" +
                                        "        \"id\": \"778\",\n" +
                                        "        \"name\": \"F020522-27778.pdf\",\n" +
                                        "        \"creation\": \"2018-11-11 16:00:00\",\n" +
                                        "        \"validation\": \"2018-06-06 16:02:20\",\n" +
                                        "        \"price\": \"31.6\",\n" +
                                        "        \"vat\": \"6.32\",\n" +
                                        "        \"total\": \"37.92\",\n" +
                                        "        \"currency\": \"eur\",\n" +
                                        "        \"status\": \"paid\",\n" +
                                        "        \"payment_type\": \"card\",\n" +
                                        "        \"products\": [\n" +
                                        "          {\n" +
                                        "            \"description\": \"Nimbus 2000\",\n" +
                                        "            \"unit_price\": \"0.80\",\n" +
                                        "            \"quantities\": \"10\",\n" +
                                        "            \"price\": \"8\"\n" +
                                        "          },\n" +
                                        "          {\n" +
                                        "            \"description\": \"Vif d'or\",\n" +
                                        "            \"unit_price\": \"0.8000\",\n" +
                                        "            \"quantities\": \"40\",\n" +
                                        "            \"price\": \"32.00\"\n" +
                                        "          }\n" +
                                        "        ]\n" +
                                        "      },\n" +
                                        "      {\n" +
                                        "        \"id\": \"779\",\n" +
                                        "        \"name\": \"F020523-27778.pdf\",\n" +
                                        "        \"creation\": \"2018-11-11 17:00:00\",\n" +
                                        "        \"validation\": \"2018-06-06 17:02:20\",\n" +
                                        "        \"price\": \"14\",\n" +
                                        "        \"vat\": \"2.8\",\n" +
                                        "        \"total\": \"16.8\",\n" +
                                        "        \"currency\": \"eur\",\n" +
                                        "        \"status\": \"waiting_for_payment\",\n" +
                                        "        \"payment_type\": \"transfer\",\n" +
                                        "        \"products\": [\n" +
                                        "          {\n" +
                                        "            \"description\": \"Baguette\",\n" +
                                        "            \"unit_price\": \"0.30\",\n" +
                                        "            \"quantities\": \"10\",\n" +
                                        "            \"price\": \"3\"\n" +
                                        "          }\n" +
                                        "        ]\n" +
                                        "      }\n" +
                                        "    ]\n" +
                                        "}"
                        )
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void getInvoices() throws IOException {
        //given
        final GetInvoicesResultResponse expectedResponse = GetInvoicesResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .responseStatus(ResponseStatus.OK)
                .invoices(
                        Arrays.asList(
                                Invoice.builder()
                                        .id("778")
                                        .name("F020522-27778.pdf")
                                        .creation(LocalDateTime.of(2018, 11, 11, 16, 0, 0))
                                        .validation(LocalDateTime.of(2018, 6, 6, 16, 2, 20))
                                        .price("31.6")
                                        .vat("6.32")
                                        .total("37.92")
                                        .currency("eur")
                                        .status("paid")
                                        .paymentType("card")
                                        .product(
                                                Product.builder()
                                                        .description("Nimbus 2000")
                                                        .unitPrice("0.80")
                                                        .quantities("10")
                                                        .price("8")
                                                        .build()
                                        )
                                        .product(
                                                Product.builder()
                                                        .description("Vif d'or")
                                                        .unitPrice("0.8000")
                                                        .quantities("40")
                                                        .price("32.00")
                                                        .build()
                                        )
                                        .build(),
                                Invoice.builder()
                                        .id("779")
                                        .name("F020523-27778.pdf")
                                        .creation(LocalDateTime.of(2018, 11, 11, 17, 0, 0))
                                        .validation(LocalDateTime.of(2018, 6, 6, 17, 2, 20))
                                        .price("14")
                                        .vat("2.8")
                                        .total("16.8")
                                        .currency("eur")
                                        .status("waiting_for_payment")
                                        .paymentType("transfer")
                                        .product(
                                                Product.builder()
                                                        .description("Baguette")
                                                        .unitPrice("0.30")
                                                        .quantities("10")
                                                        .price("3")
                                                        .build()
                                        )
                                        .build()
                        )
                        )
                .build();
        final int expectedStatusCode = 200;

        //when
        final GetInvoicesResponse result = invoicesService.getInvoices(YOUR_TOKEN);
        final Integer effectiveStatusCode = result.getStatusCode();
        final GetInvoicesResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
        assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
        assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
