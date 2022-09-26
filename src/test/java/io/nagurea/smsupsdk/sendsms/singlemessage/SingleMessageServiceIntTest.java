package io.nagurea.smsupsdk.sendsms.singlemessage;

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
class SingleMessageServiceIntTest extends TestIntBase {

    private static final String YOUR_MESSAGE = "This is text";
    private static final String YOUR_DESTINATION = "41781234567";
    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private SingleMessageService singleMessageService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/send")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                        .withQueryStringParameter("text", YOUR_MESSAGE)
                        .withQueryStringParameter("to", YOUR_DESTINATION)
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
        );
        mockServer.when(
                request()
                        .withPath("/send/simulate")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                        .withQueryStringParameter("text", YOUR_MESSAGE)
                        .withQueryStringParameter("to", YOUR_DESTINATION)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
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
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void sendMarketing() throws IOException {
         //given
         final SingleMessageResultResponse expectedResponse = SingleMessageResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
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
         final SingleMessageResponse result = singleMessageService.sendMarketing(YOUR_TOKEN, YOUR_MESSAGE, YOUR_DESTINATION);
         final Integer effectiveStatusCode = result.getStatusCode();
         final SingleMessageResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
         assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }

    @Test
    void sendAlert() throws IOException {
        //given
        final SingleMessageResultResponse expectedResponse = SingleMessageResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .responseStatus(ResponseStatus.OK)
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
        final SingleMessageResponse result = singleMessageService.sendAlert(YOUR_TOKEN, YOUR_MESSAGE, YOUR_DESTINATION);
        final Integer effectiveStatusCode = result.getStatusCode();
        final SingleMessageResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
        assertEquals("OK", effectiveResponse.getMessage());
        assertEquals(expectedResponse, effectiveResponse);

    }

    @Test
    void simulateSendMarketing() throws IOException {
        //given
        final SingleMessageResultResponse expectedResponse = SingleMessageResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .responseStatus(ResponseStatus.OK)
                .ticket(null) //there should not be ticket in the response
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
        final SingleMessageResponse result = singleMessageService.simulateSendMarketing(YOUR_TOKEN, YOUR_MESSAGE, YOUR_DESTINATION);
        final Integer effectiveStatusCode = result.getStatusCode();
        final SingleMessageResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
        assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
        assertEquals("OK", effectiveResponse.getMessage());

    }

}
