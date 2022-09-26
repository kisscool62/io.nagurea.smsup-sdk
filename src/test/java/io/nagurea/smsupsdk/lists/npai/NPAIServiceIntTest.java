package io.nagurea.smsupsdk.lists.npai;

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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class NPAIServiceIntTest extends TestIntBase {


    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private NPAIService npaiService;

    private static ClientAndServer mockServer;


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/npai")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"message\": \"OK\",\n" +
                                    "  \"list\": [\n" +
                                    "      {\n" +
                                    "          \"id\": \"5a4e49d9fc5886067c13f533\",\n" +
                                    "          \"destination\": \"41781234567\",\n" +
                                    "          \"info1\": \"Douglas\",\n" +
                                    "          \"info2\": \"Adams\",\n" +
                                    "          \"info3\": \"1952\",\n" +
                                    "          \"info4\": \"Cambridge\",\n" +
                                    "          \"campaign_id\": 47856851\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "          \"id\": \"5a4e4a00fc5886067c13f534\",\n" +
                                    "          \"destination\": \"41781234566\",\n" +
                                    "          \"info1\": \"Frank\",\n" +
                                    "          \"info2\": \"Herbert\",\n" +
                                    "          \"info3\": \"1920\",\n" +
                                    "          \"info4\": \"Tacoma\",\n" +
                                    "          \"campaign_id\": 47858459\n" +
                                    "      }\n" +
                                    "  ],\n" +
                                    "  \"totalRecords\": 2,\n" +
                                    "  \"totalDisplayRecords\": 2\n" +
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
        NPAIResultResponse expectedGetListResponse = NPAIResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .totalRecords(2)
                .totalDisplayRecords(2)
                .list(Arrays.asList(
                        NPAIContact.builder()
                                .id("5a4e49d9fc5886067c13f533")
                                .destination("41781234567")
                                .info1("Douglas")
                                .info2("Adams")
                                .info3("1952")
                                .info4("Cambridge")
                                .campaignId(47856851)
                                .build(),
                        NPAIContact.builder()
                                .id("5a4e4a00fc5886067c13f534")
                                .destination("41781234566")
                                .info1("Frank")
                                .info2("Herbert")
                                .info3("1920")
                                .info4("Tacoma")
                                .campaignId(47858459)
                                .build()
                ))
                .build();


        // when
        final NPAIResultResponse actualGetListResponse = npaiService.getNPAIList(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListResponse.getStatus());
        assertEquals("OK", actualGetListResponse.getMessage());
        assertEquals(expectedGetListResponse, actualGetListResponse);

    }
}