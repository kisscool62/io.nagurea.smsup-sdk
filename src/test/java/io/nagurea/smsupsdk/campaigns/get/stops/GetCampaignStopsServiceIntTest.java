package io.nagurea.smsupsdk.campaigns.get.stops;

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
class GetCampaignStopsServiceIntTest extends TestIntBase {

    private static final String CAMPAIGN_ID = "41781234567";
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetCampaignStopsService getCampaignStopsService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/campaign/" + CAMPAIGN_ID + "/blacklist")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"stops\": [\n" +
                                        "        {\n" +
                                        "            \"destination\": \"41781234567\",\n" +
                                        "            \"info1\": \"Ricky\",\n" +
                                        "            \"info2\": \"Gervais\",\n" +
                                        "            \"info3\": null,\n" +
                                        "            \"info4\": null,\n" +
                                        "            \"date\": \"2022-07-08 19:06:21\"\n" +
                                        "        },\n" +
                                        "        {\n" +
                                        "            \"destination\": \"41781234566\",\n" +
                                        "            \"info1\": \"Richard\",\n" +
                                        "            \"info2\": \"Dawkins\",\n" +
                                        "            \"info3\": null,\n" +
                                        "            \"info4\": null,\n" +
                                        "            \"date\": \"2022-07-08 19:06:21\"\n" +
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
     void getCampaignStops() throws IOException {
         //given
         final GetCampaignStopsResultResponse expectedResponse = GetCampaignStopsResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .stops(
                         Arrays.asList(
                                 Stop.builder()
                                         .destination("41781234567")
                                         .info1("Ricky")
                                         .info2("Gervais")
                                         .info3(null)
                                         .info4(null)
                                         .date(LocalDateTime.of(2022, 7, 8, 19, 6, 21))
                                         .build(),
                                 Stop.builder()
                                         .destination("41781234566")
                                         .info1("Richard")
                                         .info2("Dawkins")
                                         .info3(null)
                                         .info4(null)
                                         .date(LocalDateTime.of(2022, 7, 8, 19, 6, 21))
                                         .build()
                         )
                 )
                 .build();
         final int expectedStatusCode = 200;

         //when
         final GetCampaignStopsResponse result = getCampaignStopsService.getCampaignStops(YOUR_TOKEN, CAMPAIGN_ID);
         final Integer effectiveStatusCode = result.getStatusCode();
         final GetCampaignStopsResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
