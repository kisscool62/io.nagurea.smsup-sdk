package io.nagurea.smsupsdk.notifications.balance.get;

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
class GetNotificationServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private GetNotificationService getNotificationService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();
        mockServer.when(
                request()
                        .withPath("/notification/balance")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"notification\": {\n" +
                                        "        \"alert_email\": \"1\",\n" +
                                        "        \"alert_gsm\": \"1\",\n" +
                                        "        \"email\": \"ano@nyme.com\",\n" +
                                        "        \"phone_number\": \"41781234567\",\n" +
                                        "        \"alert_trigger\": \"700\"\n" +
                                        "    }\n" +
                                        "}\n" +
                                        "  "
                        )
        );
    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void getNotificationBalance() throws IOException {
        //given
        final GetNotificationResultResponse expectedResponse = GetNotificationResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .responseStatus(ResponseStatus.OK)
                .notification(
                        Notification.builder()
                                .alertEmail("1")
                                .alertGsm("1")
                                .email("ano@nyme.com")
                                .phoneNumber("41781234567")
                                .alertTrigger("700")
                            .build()
                        )
                .build();
        final int expectedStatusCode = 200;

        //when
        final GetNotificationResponse result = getNotificationService.getNotificationBalance(YOUR_TOKEN);
        final Integer effectiveStatusCode = result.getStatusCode();
        final GetNotificationResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
        assertEquals(expectedResponse.getMessage(), effectiveResponse.getMessage());
        assertEquals(expectedResponse.getStatus(), effectiveResponse.getStatus());

    }


}
