package io.nagurea.smsupsdk.notifications.balance.update;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.notifications.balance.update.body.request.Notification;
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

import static io.nagurea.smsupsdk.notifications.balance.update.body.ActivationStatus.ACTIVATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class UpdateBalanceServiceIntTest {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private UpdateBalanceService balanceService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        @SerializedName("alert_trigger")
        final String alertTrigger = "500";

        @SerializedName("alert_email")
        final String alertEmail = "1";

        @SerializedName("alert_gsm")
        final String alertGsm = "1";

        final String email = "ano@nyme.com";

        @SerializedName("phone_number")
        final String phoneNumber = "41781234567";
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);

        mockServer.when(
                request()
                        .withPath("/notification/balance")
                        .withMethod("PUT")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"notification\": {\n" +
                                        "      \"alert_trigger\": \"500\",\n" +
                                        "      \"alert_email\": \"1\",\n" +
                                        "      \"alert_gsm\": \"1\",\n" +
                                        "      \"email\": \"ano@nyme.com\",\n" +
                                        "      \"phone_number\": \"41781234567\"\n" +
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
     void updateBalance() throws IOException {
         //given
         final Notification notificationRequest = Notification.builder()
                 .alertTrigger("500")
                 .alertEmail(ACTIVATED)
                 .alertGsm(ACTIVATED)
                 .email("ano@nyme.com")
                 .phoneNumber("41781234567")
                 .build();

         final io.nagurea.smsupsdk.notifications.balance.update.body.response.Notification notificationResponse = io.nagurea.smsupsdk.notifications.balance.update.body.response.Notification.builder()
                 .alertTrigger("500")
                 .alertEmail(ACTIVATED)
                 .alertGsm(ACTIVATED)
                 .email("ano@nyme.com")
                 .phoneNumber("41781234567")
                 .build();

         final UpdateBalanceResultResponse expectedResponse = UpdateBalanceResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .notification(notificationResponse)
                 .build();
         final int expectedStatusCode = 200;


         //when
         final UpdateBalanceResponse result = balanceService.updateBalance("token", notificationRequest);
         final Integer effectiveStatusCode = result.getStatusCode();
         final UpdateBalanceResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

}
