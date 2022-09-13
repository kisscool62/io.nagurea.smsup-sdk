package io.nagurea.smsupsdk.sendsms.campaign;

import com.google.common.collect.Sets;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.common.Gsm;
import io.nagurea.smsupsdk.sendsms.common.Recipients;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class CampaignServiceIntTest {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CampaignService campaignService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object sms = new Object() {
            public final Object message = new Object() {
                public final String text = "Message via API";
                public final String pushtype = "alert";
                public final String sender = "Illidan";
                public final String delay = "2022-09-23 10:58:35";
                public final Integer unicode = 0;
            };
            public final Object recipients = new Object(){
                public final Object[] gsm = {
                        new Object(){
                            public final String gsmsmsid = "100";
                            public final String value = "41781234567";
                        },
                        new Object(){
                            public final String gsmsmsid = "101";
                            public final String value = "41781234566";
                        }
                };
            };

        };
    };

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/send")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"ticket\": \"14672468\", \n" +
                                        "  \"cost\": 2,              \n" +
                                        "  \"credits\": 642,         \n" +
                                        "  \"total\": 2,             \n" +
                                        "  \"sent\": 2,              \n" +
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
     void sendAlert() throws IOException {
         //given
         final CampaignResultResponse expectedResponse = CampaignResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .ticket("14672468")
                 .cost(2)
                 .credits(642)
                 .total(2)
                 .sent(2)
                 .blacklisted(0)
                 .duplicated(0)
                 .invalid(0)
                 .npai(0)
                 .build();
         final int expectedStatusCode = 200;
         final Recipients recipients = Recipients.builder().gsm(
                 Sets.newHashSet(
                     Gsm.builder()
                             .gsmsmsid("100").value("41781234567").build(),
                     Gsm.builder()
                             .gsmsmsid("101").value("41781234566").build()
                 )
         ).build();

         final AlertOptionalArguments alertOptionalArgument = AlertOptionalArguments.builder()
                 .sender(Sender.build("Illidan"))
                 .delay(Delay.builder()
                         .value(LocalDateTime.of(2022, 9, 23, 10, 58, 35))
                         .build())
                 .unicode(0)
                 .build();

         //when
         final CampaignResponse result = campaignService.sendAlert("token", "Message via API", recipients, alertOptionalArgument);
         final Integer effectiveStatusCode = result.getStatusCode();
         final CampaignResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

}
