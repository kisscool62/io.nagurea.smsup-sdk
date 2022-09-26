package io.nagurea.smsupsdk.lists.get.lists;

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
class GetListsServiceIntTest extends TestIntBase {


    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetListsService getListsService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/lists")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"message\": \"OK\",\n" +
                                    "  \"lists\": [\n" +
                                    "      {\n" +
                                    "          \"id\": \"50433\",\n" +
                                    "          \"name\": \"Liste API\",\n" +
                                    "          \"date\": \"2017-12-10 10:36:49\",\n" +
                                    "          \"count\": \"5\"\n" +
                                    "      },\n" +
                                    "      {\n" +
                                    "          \"id\": \"50758\",\n" +
                                    "          \"name\": \"2017-12-16 13:42:23\",\n" +
                                    "          \"date\": \"2017-12-16 13:42:23\",\n" +
                                    "          \"count\": \"15000\"\n" +
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
    void getLists() throws IOException {
        // given
        GetListsResultResponse expectedGetListsResponse = GetListsResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .lists(Arrays.asList(
                        ContactList.builder()
                                .id("50433")
                                .name("Liste API")
                                .date(LocalDateTime.of(2017, 12, 10, 10, 36, 49))
                                .count("5")
                                .build(),
                        ContactList.builder()
                                .id("50758")
                                .name("2017-12-16 13:42:23")
                                .date(LocalDateTime.of(2017, 12, 16, 13, 42,23))
                                .count("15000")
                                .build()))
                .build();


        // when
        final GetListsResultResponse actualGetListsResponse = getListsService.getLists(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListsResponse.getStatus());
        assertEquals("OK", actualGetListsResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualGetListsResponse);

    }
}