package io.nagurea.smsupsdk.sendmessages.unitmessage;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

class UnitMessageIntTest {

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/SEND")
                        .withMethod("GET")
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"ticket\": \"14672468\", \n" +
                                        "  \"cost\": 1,              \n" +
                                        "  \"credits\": 642,         \n" +
                                        "  \"total\": 1,             \n" +
                                        "  \"sent\": 1,              \n" +
                                        "  \"blacklisted\": 0,       \n" +
                                        "  \"duplicated\": 0,        \n" +
                                        "  \"invalid\": 0,           \n" +
                                        "  \"npai\": 0               \n" +
                                        "}"
                        )
        )
        ;
    }

     @Test
     void okdout() throws IOException {
         //given
         final UnitMessageResultResponse expectedResponse = UnitMessageResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .ticket("14672468")
                 .cost(1)
                 .credits(642)
                 .total(1)
                 .sent(1)
                 .blacklisted(0)
                 .duplicated(0)
                 .invalid(0)
                 .npai(0)
                 .build();
         final int expectedStatusCode = 200;

         //when
         final UnitMessageResponse result = new UnitMessageService("http://localhost:4242").sendMarketing("token", "This is a text", "075655655");
         final Integer effectiveStatusCode = result.getStatusCode();
         final UnitMessageResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);

    }
}
