package io.nagurea.smsupsdk.accountmanaging.account.retrieve;

import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.AccountInfo;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.RetrieveAccountResultResponse;
import io.nagurea.smsupsdk.common.TestIntBase;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class RetrieveAccountServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private RetrieveAccountService retrieveAccountService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/account")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "    \"status\": 1,\n" +
                                    "    \"message\": \"OK\",\n" +
                                    "    \"account\": {\n" +
                                    "      \"client_id\": \"1010101\",\n" +
                                    "      \"email\": \"mr@robot.com\",\n" +
                                    "      \"firstname\": \"Elliot\",\n" +
                                    "      \"lastname\": \"Alderson\",\n" +
                                    "      \"city\": \"New York\",\n" +
                                    "      \"type\": \"private\",\n" +
                                    "      \"company\": \"Allsafe\",\n" +
                                    "      \"phone\": \"41781234567\",\n" +
                                    "      \"address1\": \"unknown address\",\n" +
                                    "      \"address2\": null,\n" +
                                    "      \"zip\": \"10040\",\n" +
                                    "      \"country\": \"\",\n" +
                                    "      \"country_code\": \"US\",\n" +
                                    "      \"lang\": \"EN\",\n" +
                                    "      \"credits\": \"0\",\n" +
                                    "      \"unlimited\": \"1\",\n" +
                                    "      \"description\": \"Fsociety\",\n" +
                                    "      \"senderid\": \"\",\n" +
                                    "      \"status\": \"1\"\n" +
                                    "    }\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void retrieveAccount() throws IOException {
        // given
        RetrieveAccountResultResponse expectedGetListsResponse = RetrieveAccountResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .account(AccountInfo.builder()
                        .clientId("1010101")
                        .email("mr@robot.com")
                        .firstname("Elliot")
                        .lastname("Alderson")
                        .city("New York")
                        .type("private")
                        .company("Allsafe")
                        .phone("41781234567")
                        .address1("unknown address")
                        .address2(null)
                        .zip("10040")
                        .country("")
                        .countryCode("US")
                        .lang("EN")
                        .credits("0")
                        .unlimited("1")
                        .description("Fsociety")
                        .senderid("")
                        .status("1")
                        .build())
                .build();


        // when
        final RetrieveAccountResultResponse actualAccountResponse = retrieveAccountService.retrieveAccount(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualAccountResponse.getStatus());
        assertEquals("OK", actualAccountResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualAccountResponse);

    }


}