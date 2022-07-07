package io.nagurea.smsupsdk.campaigns.get.campaign;

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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class GetCampaignServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String CAMPAIGN_ID = "41781234567";
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetCampaignService getCampaignService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/campaign/" + CAMPAIGN_ID)
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"campaign\": [\n" +
                                        "        {\n" +
                                        "            \"id\": \"18969398\",\n" +
                                        "            \"sender\": \"BESTSHOES\",\n" +
                                        "            \"text\": \"Special offer : Buy one shoe and get the second one for free\",\n" +
                                        "            \"date\": \"2018-07-04 11:37:16\",\n" +
                                        "            \"cost\": \"2128\",\n" +
                                        "            \"lists\": [\n" +
                                        "                {\n" +
                                        "                    \"id\": \"12345\"\n" +
                                        "                },\n" +
                                        "                {\n" +
                                        "                    \"id\": \"45742\"\n" +
                                        "                }\n" +
                                        "            ],\n" +
                                        "            \"delivered\": \"1958\",\n" +
                                        "            \"error\": \"0\",\n" +
                                        "            \"expired\": \"0\",\n" +
                                        "            \"network_error\": \"0\",\n" +
                                        "            \"stop\": \"0\",\n" +
                                        "            \"npai\": \"0\"\n" +
                                        "        }\n" +
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
     void sendMarketing() throws IOException {
         //given
         final GetCampaignResultResponse expectedResponse = GetCampaignResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .campaign(
                         Collections.singletonList(
                                 Campaign.builder()
                                         .id("18969398")
                                         .sender("BESTSHOES")
                                         .text("Special offer : Buy one shoe and get the second one for free")
                                         .date(LocalDateTime.of(2018, 7, 4, 11, 37, 16))
                                         .cost("2128")
                                         .lists(
                                                 Arrays.asList(
                                                         ListId.builder()
                                                                 .id("12345")
                                                                 .build(),
                                                         ListId.builder()
                                                                 .id("45742")
                                                                 .build()
                                                 )
                                         )
                                         .delivered("1958")
                                         .error("0")
                                         .expired("0")
                                         .networkError("0")
                                         .stop("0")
                                         .npai("0")
                                         .build()
                         )
                 )
                 .build();
         final int expectedStatusCode = 200;

         //when
         final GetCampaignResponse result = getCampaignService.getCampaign(YOUR_TOKEN, CAMPAIGN_ID);
         final Integer effectiveStatusCode = result.getStatusCode();
         final GetCampaignResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
