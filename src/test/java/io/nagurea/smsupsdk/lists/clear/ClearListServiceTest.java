package io.nagurea.smsupsdk.lists.clear;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class ClearListServiceTest {
    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String LIST_ID = "2";

    /**
     * Useless. Only here to show how services could be used with Spring
     */
    @Autowired
    private ClearListService clearListService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer() {
        ConfigurationProperties.logLevel("DEBUG");
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/list/" + LIST_ID + "/npai/clear")
                        .withMethod("PUT")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"removed\": 2\n" +
                                        "}"
                        )
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void clear() throws IOException {
        //given
        //expected results
        final ClearListResultResponse expectedResponse = ClearListResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .removed(2)
                .build();
        final int expectedStatusCode = 200;

        //given arguments

        //when
        final ClearListResponse result = clearListService.clear(YOUR_TOKEN, LIST_ID);
        final Integer effectiveStatusCode = result.getStatusCode();
        final ClearListResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
    }


}