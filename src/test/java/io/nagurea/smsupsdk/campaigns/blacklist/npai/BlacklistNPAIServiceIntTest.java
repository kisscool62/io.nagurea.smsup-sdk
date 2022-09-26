package io.nagurea.smsupsdk.campaigns.blacklist.npai;

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
class BlacklistNPAIServiceIntTest extends TestIntBase {

    private static final String CAMPAIGN_ID = "6666723";
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private BlacklistNPAIService blacklistNPAIService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/campaign/" + CAMPAIGN_ID + "/npai")
                        .withMethod("PUT")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"NPAI\": 2\n" +
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
         final BlacklistNPAIResultResponse expectedResponse = BlacklistNPAIResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .npai(2)
                 .build();
         final int expectedStatusCode = 200;

         //when
         final BlacklistNPAIResponse result = blacklistNPAIService.blacklistNPAI(YOUR_TOKEN, CAMPAIGN_ID);
         final Integer effectiveStatusCode = result.getStatusCode();
         final BlacklistNPAIResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
