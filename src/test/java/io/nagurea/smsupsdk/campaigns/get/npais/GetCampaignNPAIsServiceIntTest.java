package io.nagurea.smsupsdk.campaigns.get.npais;

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
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class GetCampaignNPAIsServiceIntTest extends TestIntBase {

    private static final String CAMPAIGN_ID = "18969398";
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetCampaignNPAIsService getCampaignNPAIsService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/campaign/" + CAMPAIGN_ID + "/npai")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"npais\": [\n" +
                                        "        {\n" +
                                        "            \"destination\": \"41781234567\",\n" +
                                        "            \"info1\": \"Ricky\",\n" +
                                        "            \"info2\": \"Gervais\",\n" +
                                        "            \"info3\": null,\n" +
                                        "            \"info4\": null,\n" +
                                        "            \"date\": \"2022-07-08 19:27:41\"\n" +
                                        "        },\n" +
                                        "        {\n" +
                                        "            \"destination\": \"41781234566\",\n" +
                                        "            \"info1\": \"Richard\",\n" +
                                        "            \"info2\": \"Dawkins\",\n" +
                                        "            \"info3\": null,\n" +
                                        "            \"info4\": null,\n" +
                                        "            \"date\": \"2022-07-08 19:27:41\"\n" +
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
     void getCampaignNPAIs() throws IOException {
         //given
         final GetCampaignNPAIsResultResponse expectedResponse = GetCampaignNPAIsResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .npais(
                         Arrays.asList(
                                 NPAI.builder()
                                         .destination("41781234567")
                                         .info1("Ricky")
                                         .info2("Gervais")
                                         .info3(null)
                                         .info4(null)
                                         .date(LocalDateTime.of(2022, 7, 8, 19, 27, 41))
                                         .build(),
                                 NPAI.builder()
                                         .destination("41781234566")
                                         .info1("Richard")
                                         .info2("Dawkins")
                                         .info3(null)
                                         .info4(null)
                                         .date(LocalDateTime.of(2022, 7, 8, 19, 27, 41))
                                         .build()
                         )
                 )
                 .build();
         final int expectedStatusCode = 200;

         //when
         final GetCampaignNPAIsResponse result = getCampaignNPAIsService.getCampaignNPAIs(YOUR_TOKEN, CAMPAIGN_ID);
         final Integer effectiveStatusCode = result.getStatusCode();
         final GetCampaignNPAIsResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
