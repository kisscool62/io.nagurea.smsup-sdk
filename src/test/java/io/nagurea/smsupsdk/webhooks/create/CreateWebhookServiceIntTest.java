package io.nagurea.smsupsdk.webhooks.create;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.webhooks.create.body.WebhookInfo;
import io.nagurea.smsupsdk.webhooks.create.common.WebhookType;
import io.nagurea.smsupsdk.webhooks.create.response.Webhook;
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
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class CreateWebhookServiceIntTest {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CreateWebhookService createWebhookService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        final Object webhook = new Object() {
            final String type = "DLR";
            final String url = "https://yourserverurl.com";

        };
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);

        mockServer.when(
                request()
                        .withPath("/webhook")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "  {\n" +
                                "    \"status\": 1,\n" +
                                "    \"message\": \"OK\",\n" +
                                "    \"webhook\": {\n" +
                                "        \"webhook_id\": \"66\",\n" +
                                "        \"type\": \"MO\",\n" +
                                "        \"url\": \"https://yourserverurl.com\"\n" +
                                "    }\n" +
                                "  }"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void createWebhook() throws IOException {
         //given
         final CreateWebhookResultResponse expectedResponse = CreateWebhookResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .webhook(
                         Webhook.builder()
                                 .id("66")
                                 .type(WebhookType.MO)
                                 .url("https://yourserverurl.com")
                                 .build()
                 )
                 .build();
         final int expectedStatusCode = 200;

         final WebhookInfo webhookInfo = WebhookInfo.builder()
                 .type(WebhookType.DLR)
                 .url("https://yourserverurl.com")
                 .build();

         //when
         final CreateWebhookResponse result = createWebhookService.createWebhook("token", webhookInfo);
         final Integer effectiveStatusCode = result.getStatusCode();
         final CreateWebhookResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }


}
