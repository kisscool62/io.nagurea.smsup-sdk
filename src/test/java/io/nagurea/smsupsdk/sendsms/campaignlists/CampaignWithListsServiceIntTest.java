package io.nagurea.smsupsdk.sendsms.campaignlists;

import com.google.common.collect.Sets;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.CampaignWithListResponse;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.CampaignWithListResultResponse;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.CampaignWithListService;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.ListId;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class CampaignWithListsServiceIntTest {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CampaignWithListService campaignWithListService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        final Object sms = new Object() {
           final Object message = new Object(){
               final String text = "Message via API";
               final String pushtype = "alert";
               final String sender = "Ganondorf";
               final String delay = "2022-06-19 10:21:33";
               final Integer unicode = 0;
           };
           final Object[] lists = {
                   new Object(){
                       final Integer value = 45190;
                   },
                   new Object(){
                       final Integer value = 47854;
                   }
           };
        };
    };

    private static final Object EXPECTED_JSON_OBJECT_SIMULATE = new Object() {
        final Object sms = new Object() {
            final Object message = new Object(){
                final String text = "Message via API";
                final String pushtype = "alert";
                final String sender = "Sephiroth";
                final String delay = "2022-06-20 10:02:07";
            };
            final Object[] lists = {
                    new Object(){
                        final Integer value = 45190;
                    },
                    new Object(){
                        final Integer value = 47854;
                    }
            };
        };
    };

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);

        mockServer.when(
                request()
                        .withPath("/send/lists")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"ticket\": \"14672468\",\n" +
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

        mockServer.when(
                request()
                        .withPath("/send/lists/simulate")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,      \n" +
                                        "  \"message\": \"OK\",\n" +
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
         final CampaignWithListResultResponse expectedResponse = CampaignWithListResultResponse.builder()
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
         final Set<ListId> lists = Sets.newHashSet(
                 ListId.builder().value(45190).build(),
                 ListId.builder().value(47854).build());

         final AlertOptionalArguments alertOptionalArgument = AlertOptionalArguments.builder()
                 .unicode(0)
                 .delay(Delay.builder().value(LocalDateTime.of(2022, 6, 19, 10, 21, 33)).build())
                 .sender(Sender.build("Ganondorf"))
                 .build();

         //when
         final CampaignWithListResponse result = campaignWithListService.sendAlert("token", "Message via API", lists, alertOptionalArgument);
         final Integer effectiveStatusCode = result.getStatusCode();
         final CampaignWithListResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

    @Test
    void simulateSendAlert() throws IOException {
        //given
        final CampaignWithListResultResponse expectedResponse = CampaignWithListResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .responseStatus(ResponseStatus.OK)
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
        final Set<ListId> lists = Sets.newHashSet(
                ListId.builder().value(45190).build(),
                ListId.builder().value(47854).build());
        final AlertOptionalArguments alertOptionalArgument = AlertOptionalArguments.builder()
                .delay(Delay.builder().value(LocalDateTime.of(2022, 6, 20, 10, 2, 7)).build())
                .sender(Sender.build("Sephiroth"))
                .build();

        //when
        final CampaignWithListResponse result = campaignWithListService.simulateSendAlert("token", "Message via API", lists, alertOptionalArgument);
        final Integer effectiveStatusCode = result.getStatusCode();
        final CampaignWithListResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
        assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
        assertEquals("OK", effectiveResponse.getMessage());

    }

}
