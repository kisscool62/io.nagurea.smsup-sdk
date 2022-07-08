package io.nagurea.smsupsdk.campaigns.get.replies;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class GetCampaignRepliesServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String CAMPAIGN_ID = "18969398";
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetCampaignRepliesService getCampaignRepliesService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/campaign/" + CAMPAIGN_ID + "/mo")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"replies\": [\n" +
                                        "        {\n" +
                                        "            \"destination\": 41781234567,\n" +
                                        "            \"info1\": \"Ricky\",\n" +
                                        "            \"info2\": \"Gervais\",\n" +
                                        "            \"date\": \"2022-07-08 19:36:49\"\n" +
                                        "        },\n" +
                                        "        {\n" +
                                        "            \"destination\": 41781234566,\n" +
                                        "            \"info1\": \"Richard\",\n" +
                                        "            \"info2\": \"Dawkins\",\n" +
                                        "            \"date\": \"2022-07-08 19:36:49\"\n" +
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
     void setGetCampaignReplies() throws IOException {
         //given
         final GetCampaignRepliesResultResponse expectedResponse = GetCampaignRepliesResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .replies(
                         Arrays.asList(
                                 Reply.builder()
                                         .destination("41781234567")
                                         .info1("Ricky")
                                         .info2("Gervais")
                                         .date(LocalDateTime.of(2022, 7, 8, 19, 36, 49))
                                         .build(),
                                 Reply.builder()
                                         .destination("41781234566")
                                         .info1("Richard")
                                         .info2("Dawkins")
                                         .date(LocalDateTime.of(2022, 7, 8, 19, 36, 49))
                                         .build()
                         )
                 )
                 .build();
         final int expectedStatusCode = 200;

         //when
         final GetCampaignRepliesResponse result = getCampaignRepliesService.getCampaignReplies(YOUR_TOKEN, CAMPAIGN_ID);
         final Integer effectiveStatusCode = result.getStatusCode();
         final GetCampaignRepliesResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
