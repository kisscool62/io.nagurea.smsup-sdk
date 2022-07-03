package io.nagurea.smsupsdk.lists.blacklist;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.lists.get.list.Contact;
import io.nagurea.smsupsdk.lists.get.list.GetListResultResponse;
import io.nagurea.smsupsdk.lists.get.list.GetListService;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class BlacklistServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private BlacklistService blacklistService;

    private static ClientAndServer mockServer;


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/blacklist")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{ \n" +
                                    "    \"status\": 1,\n" +
                                    "    \"message\": \"OK\",\n" +
                                    "    \"list\": [\n" +
                                    "      {\n" +
                                    "          \"id\": \"5a4e49d9fc5886067c13f533\",\n" +
                                    "          \"destination\": \"41781234567\",\n" +
                                    "          \"info1\": \"Isaac\",\n" +
                                    "          \"info2\": \"Asimov\",\n" +
                                    "          \"info3\": \"1919\",\n" +
                                    "          \"info4\": \"Petrovichi\",\n" +
                                    "          \"campaign_id\": 47856851\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "          \"id\": \"5a4e4a00fc5886067c13f534\",\n" +
                                    "          \"destination\": \"41781234566\",\n" +
                                    "          \"info1\": \"Howard\",\n" +
                                    "          \"info2\": \"Phillips\",\n" +
                                    "          \"info3\": \"Lovecraft\",\n" +
                                    "          \"info4\": \"\",\n" +
                                    "          \"campaign_id\": 47858459\n" +
                                    "      }\n" +
                                    "    ],\n" +
                                    "    \"totalRecords\": 2,\n" +
                                    "    \"totalDisplayRecords\": 2\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void getBlacklist() throws IOException {
        // given
        BlackistResultResponse expectedGetListResponse = BlackistResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .totalRecords(2)
                .totalDisplayRecords(2)
                .list(Arrays.asList(
                        BlacklistedContact.builder()
                                .id("5a4e49d9fc5886067c13f533")
                                .destination("41781234567")
                                .info1("Isaac")
                                .info2("Asimov")
                                .info3("1919")
                                .info4("Petrovichi")
                                .campaignId(47856851)
                                .build(),
                        BlacklistedContact.builder()
                                .id("5a4e4a00fc5886067c13f534")
                                .destination("41781234566")
                                .info1("Howard")
                                .info2("Phillips")
                                .info3("Lovecraft")
                                .info4("")
                                .campaignId(47858459)
                                .build()
                ))
                .build();


        // when
        final BlackistResultResponse actualGetListResponse = blacklistService.getBlacklist(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListResponse.getStatus());
        assertEquals("OK", actualGetListResponse.getMessage());
        assertEquals(expectedGetListResponse, actualGetListResponse);

    }
}