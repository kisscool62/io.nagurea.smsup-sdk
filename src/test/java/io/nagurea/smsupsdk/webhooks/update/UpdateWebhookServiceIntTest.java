package io.nagurea.smsupsdk.webhooks.update;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.webhooks.create.common.WebhookType;
import io.nagurea.smsupsdk.webhooks.update.body.WebhookBody;
import io.nagurea.smsupsdk.webhooks.update.body.WebhookInfo;
import io.nagurea.smsupsdk.webhooks.update.response.UpdateWebhookResultResponse;
import io.nagurea.smsupsdk.webhooks.update.response.Webhook;
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
class UpdateWebhookServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;
    private static final String WEBHOOK_ID = "66";

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private UpdateWebhookService updateWebhookService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object webhook = new Object() {
            public final String type = "STOP";
            public final String url = "https://mywebhook.com/stop";
        };
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/webhook/" + WEBHOOK_ID)
                        .withMethod("PUT")
                        .withHeader("Authorization", EXPECTED_TOKEN)
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"message\": \"OK\",\n" +
                                    "  \"webhook\": {\n" +
                                    "      \"webhook_id\": \"66\",\n" +
                                    "      \"type\": \"STOP\",\n" +
                                    "      \"url\": \"https://mywebhook.com/stop\"\n" +
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
    void updateWebhook() throws IOException {
        // given
        UpdateWebhookResultResponse expectedResponse = UpdateWebhookResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .webhook(
                        Webhook.builder()
                                .id("66")
                                .type(WebhookType.STOP)
                                .url("https://mywebhook.com/stop")
                                .build()
                )
                .build();

        final WebhookInfo webhookInfo = WebhookInfo.builder()
                .type(WebhookType.STOP)
                .url("https://mywebhook.com/stop")
                .build();

        // when
        final UpdateWebhookResultResponse actualResponse = updateWebhookService.updateWebhook(YOUR_TOKEN, WEBHOOK_ID, webhookInfo).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualResponse.getStatus());
        assertEquals("OK", actualResponse.getMessage());
        assertEquals(expectedResponse, actualResponse);

    }
}