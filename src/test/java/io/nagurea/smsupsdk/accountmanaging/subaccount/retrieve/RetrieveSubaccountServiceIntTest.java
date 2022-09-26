package io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve;

import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.RetrieveSubaccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.RetrieveSubaccountsResultResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.SubaccountInfo;
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
class RetrieveSubaccountServiceIntTest extends TestIntBase {

    private static final String SUB_ACCCOUNT_ID = "1010101";

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private RetrieveSubaccountService retrieveSubaccountService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();

        mockServer.when(
                request()
                        .withPath("/sub-account/" + SUB_ACCCOUNT_ID)
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"sub-account\":\n" +
                                        "      {\n" +
                                        "        \"client_id\": \"1010101\",\n" +
                                        "        \"email\": \"S@ch.fr\",\n" +
                                        "        \"firstname\": \"Yoh\",\n" +
                                        "        \"lastname\": \"Asakura\",\n" +
                                        "        \"city\": \"Izumo\",\n" +
                                        "        \"phone\": \"41781234567\",\n" +
                                        "        \"address1\": \"Somewhere\",\n" +
                                        "        \"address2\": \"Elsewere\",\n" +
                                        "        \"zip\": \"36520\",\n" +
                                        "        \"country\": \"\",\n" +
                                        "        \"country_code\": \"FR\",\n" +
                                        "        \"lang\": \"FR\",\n" +
                                        "        \"credits\": \"0\",\n" +
                                        "        \"unlimited\": \"1\",\n" +
                                        "        \"description\": \"Shaman King Company\",\n" +
                                        "        \"senderid\": \"\",\n" +
                                        "        \"status\": \"1\"\n" +
                                        "      }\n" +
                                        "}"
                        )
        );

        mockServer.when(
                request()
                        .withPath("/sub-accounts")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"sub-accounts\": [\n" +
                                        "    {\n" +
                                        "      \"client_id\": \"1010101\",\n" +
                                        "      \"email\": \"S@ch.fr\",\n" +
                                        "      \"firstname\": \"Yoh\",\n" +
                                        "      \"lastname\": \"Asakura\",\n" +
                                        "      \"city\": \"Izumo\",\n" +
                                        "      \"phone\": \"41781234567\",\n" +
                                        "      \"address1\": \"Somewhere\",\n" +
                                        "      \"address2\": \"Elsewere\",\n" +
                                        "      \"zip\": \"36520\",\n" +
                                        "      \"country\": \"\",\n" +
                                        "      \"country_code\": \"FR\",\n" +
                                        "      \"lang\": \"FR\",\n" +
                                        "      \"credits\": \"0\",\n" +
                                        "      \"unlimited\": \"1\",\n" +
                                        "      \"description\": \"Shaman King Company\",\n" +
                                        "      \"senderid\": \"\",\n" +
                                        "      \"status\": \"1\"\n" +
                                        "    }\n" +
                                        "  ]\n" +
                                        "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void retrieveSubaccount() throws IOException {
        // given
        RetrieveSubaccountResultResponse expectedGetListsResponse = RetrieveSubaccountResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .subaccount(SubaccountInfo.builder()
                        .clientId("1010101")
                        .email("S@ch.fr")
                        .firstname("Yoh")
                        .lastname("Asakura")
                        .city("Izumo")
                        .phone("41781234567")
                        .address1("Somewhere")
                        .address2("Elsewere")
                        .zip("36520")
                        .country("")
                        .countryCode("FR")
                        .lang("FR")
                        .credits("0")
                        .unlimited("1")
                        .description("Shaman King Company")
                        .senderid("")
                        .status("1")
                        .build())
                .build();


        // when
        final RetrieveSubaccountResultResponse actualAccountResponse = retrieveSubaccountService.retrieveSubaccount(YOUR_TOKEN, SUB_ACCCOUNT_ID).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualAccountResponse.getStatus());
        assertEquals("OK", actualAccountResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualAccountResponse);

    }

    @Test
    void retrieveSubaccounts() throws IOException {
        // given
        RetrieveSubaccountsResultResponse expectedGetListsResponse = RetrieveSubaccountsResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .subaccount(SubaccountInfo.builder()
                        .clientId("1010101")
                        .email("S@ch.fr")
                        .firstname("Yoh")
                        .lastname("Asakura")
                        .city("Izumo")
                        .phone("41781234567")
                        .address1("Somewhere")
                        .address2("Elsewere")
                        .zip("36520")
                        .country("")
                        .countryCode("FR")
                        .lang("FR")
                        .credits("0")
                        .unlimited("1")
                        .description("Shaman King Company")
                        .senderid("")
                        .status("1")
                        .build())
                .build();


        // when
        final RetrieveSubaccountsResultResponse actualAccountResponse = retrieveSubaccountService.retrieveSubaccounts(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualAccountResponse.getStatus());
        assertEquals("OK", actualAccountResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualAccountResponse);

    }


}