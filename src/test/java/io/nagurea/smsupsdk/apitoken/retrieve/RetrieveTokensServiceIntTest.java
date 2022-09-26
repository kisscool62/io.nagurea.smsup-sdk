package io.nagurea.smsupsdk.apitoken.retrieve;

import io.nagurea.smsupsdk.common.TestIntBase;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.lists.get.lists.ContactList;
import io.nagurea.smsupsdk.lists.get.lists.GetListsResultResponse;
import io.nagurea.smsupsdk.lists.get.lists.GetListsService;
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
class RetrieveTokensServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private RetrieveTokensService retrieveTokensService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/token")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"message\": \"OK\",\n" +
                                    "  \"tokens\": [\n" +
                                    "      {\n" +
                                    "          \"name\": \"Best token ever\",\n" +
                                    "          \"api_token_id\": 15,\n" +
                                    "          \"api_token\": \"yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMDg2NX0.ZnGgbDC0OI3hPm2UXyl4rxU9JlpMTMBcTJT8RVgJbtQ\",\n" +
                                    "          \"created_at\": \"2018-02-20 11:01:05\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "          \"name\": \"Security much wow\",\n" +
                                    "          \"api_token_id\": 17,\n" +
                                    "          \"api_token\": \"yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMTAzN30.U7zCuSxK5zFcaockRvoBRUxVaPOYoK2v3t6fre5Cb4k\",\n" +
                                    "          \"created_at\": \"2018-02-20 11:03:57\"\n" +
                                    "      }\n" +
                                    "  ]\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void retrieveTokens() throws IOException {
        // given
        RetrieveTokensResultResponse expectedGetListsResponse = RetrieveTokensResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .tokens(Arrays.asList(
                        TokenInfo.builder()
                                .name("Best token ever")
                                .apiTokenId(15)
                                .apiToken("yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMDg2NX0.ZnGgbDC0OI3hPm2UXyl4rxU9JlpMTMBcTJT8RVgJbtQ")
                                .createdAt(LocalDateTime.of(2018, 2, 20, 11, 1, 5))
                                .build(),
                        TokenInfo.builder()
                                .name("Security much wow")
                                .apiTokenId(17)
                                .apiToken("yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMTAzN30.U7zCuSxK5zFcaockRvoBRUxVaPOYoK2v3t6fre5Cb4k")
                                .createdAt(LocalDateTime.of(2018, 2, 20, 11, 3, 57))
                                .build()
                        ))
                .build();


        // when
        final RetrieveTokensResultResponse actualGetListsResponse = retrieveTokensService.retrieveTokens(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListsResponse.getStatus());
        assertEquals("OK", actualGetListsResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualGetListsResponse);

    }
}