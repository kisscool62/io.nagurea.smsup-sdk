package io.nagurea.smsupsdk.accountmanaging.dataretention.get;

import io.nagurea.smsupsdk.accountmanaging.dataretention.DataRetentionResult;
import io.nagurea.smsupsdk.accountmanaging.dataretention.get.response.GetDataRetentionResultResponse;
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
class GetDataRetentionServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetDataRetentionService getDataRetentionService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/retention")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "    \"status\": 1,\n" +
                                    "    \"message\": \"OK\",\n" +
                                    "    \"data_retention\": {\n" +
                                    "        \"message\": \"2m\",\n" +
                                    "        \"list\": \"2d\",\n" +
                                    "        \"survey\": \"5m\",\n" +
                                    "        \"campaign\": \"5m\"\n" +
                                    "    }\n" +
                                    "}\n" +
                                    "     "
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
        GetDataRetentionResultResponse expectedGetListsResponse = GetDataRetentionResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .dataRetention(
                        DataRetentionResult.builder()
                                .message("2m")
                                .list("2d")
                                .survey("5m")
                                .campaign("5m")
                                .build()
                        )
                .build();


        // when
        final GetDataRetentionResultResponse actualAccountResponse = getDataRetentionService.getDataRetention(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualAccountResponse.getStatus());
        assertEquals("OK", actualAccountResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualAccountResponse);

    }


}