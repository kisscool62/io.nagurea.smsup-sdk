package io.nagurea.smsupsdk.sendsms.cancelcampaign;

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
class CancelCampaignServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String CAMPAIGN_ID = "147723355";
    private static final int CREDITS = 642;
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CancelCampaignService cancelCampaignService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/send" + "/" + CAMPAIGN_ID)
                        .withMethod("DELETE")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"credits\": 642\n" +
                                "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void cancel() throws IOException {
         //given
         final CancelCampaignResultResponse expectedResponse = CancelCampaignResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .credits(CREDITS)
                 .build();
         final int expectedStatusCode = 200;

         //when
         final CancelCampaignResponse result = cancelCampaignService.cancel(YOUR_TOKEN, CAMPAIGN_ID);
         final Integer effectiveStatusCode = result.getStatusCode();
         final CancelCampaignResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());
         assertEquals(expectedResponse.getCredits(), effectiveResponse.getCredits());

    }


}
