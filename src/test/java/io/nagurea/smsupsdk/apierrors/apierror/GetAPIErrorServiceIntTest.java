package io.nagurea.smsupsdk.apierrors.apierror;

import io.nagurea.smsupsdk.apierrors.common.APIError;
import io.nagurea.smsupsdk.apierrors.common.Headers;
import io.nagurea.smsupsdk.apitoken.retrieve.RetrieveTokensResultResponse;
import io.nagurea.smsupsdk.apitoken.retrieve.RetrieveTokensService;
import io.nagurea.smsupsdk.apitoken.retrieve.TokenInfo;
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
class GetAPIErrorServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetAPIErrorService getAPIErrorService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/errors/123")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"message\": \"OK\",\n" +
                                    "  \"data\": {\n" +
                                    "      \"log_id\": \"123\",\n" +
                                    "      \"date\": \"2022-01-01 10:00:00\",\n" +
                                    "      \"method\": \"POST\",\n" +
                                    "      \"url\": \"api.smsfactor.com/send\",\n" +
                                    "      \"error_code\": \"-3\",\n" +
                                    "      \"ip\": \"127.0.0.1\",\n" +
                                    "      \"headers\": \"Content-Type: application/xml\"\\nHost: api.smsfactor.com\",\n" +
                                    "      \"query_param\": \"\",\n" +
                                    "      \"body\":\"{\\\"message\\\":{\\\"text\\\":\\\"Hello, how are you ?\\\",\\\"sender\\\":\\\"Me\\\"},\\\"recipients\\\":{\\\"gsm\\\":\\\"33611111111\\\"}}\"\n" +
                                    "  }\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void getAPIError() throws IOException {
        // given
        GetAPIErrorResultResponse expectedGetListsResponse = GetAPIErrorResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .data(
                        APIError.builder()
                                .logId("123")
                                .date(LocalDateTime.of(2022, 1, 1, 10, 0, 0))
                                .method("POST")
                                .url("api.smsfactor.com/send")
                                .errorCode("-3")
                                .ip("127.0.0.1")
                                .headers(
                                        Headers.builder()
                                                .contentType("application/xml")
                                                .host("api.smsfactor.com")
                                                .build()
                                )
                                .queryParam("")
                                .body("{\"message\":{\"text\":\"Hello, how are you ?\",\"sender\":\"Me\"},\"recipients\":{\"gsm\":\"33611111111\"}}")
                                .build()

                )
                .build();


        // when
        final GetAPIErrorResultResponse actualGetListsResponse = getAPIErrorService.getAPIError(YOUR_TOKEN, "123").getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListsResponse.getStatus());
        assertEquals("OK", actualGetListsResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualGetListsResponse);

    }


}