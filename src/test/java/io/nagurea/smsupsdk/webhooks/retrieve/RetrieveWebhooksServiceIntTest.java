package io.nagurea.smsupsdk.webhooks.retrieve;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.webhooks.create.common.WebhookType;
import io.nagurea.smsupsdk.webhooks.retrieve.response.Webhook;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class RetrieveWebhooksServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private RetrieveWebhooksService retrieveWebhooksService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/webhook")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "    \"status\": 1,\n" +
                                    "    \"message\": \"OK\",\n" +
                                    "    \"webhooks\": [\n" +
                                    "        {\n" +
                                    "            \"webhook_id\": \"66\",\n" +
                                    "            \"type\": \"DLR\",\n" +
                                    "            \"url\": \"https://mywebhook.com/dlr\",\n" +
                                    "            \"last_http_code\": 200\n" +
                                    "        },\n" +
                                    "        {\n" +
                                    "            \"webhook_id\": \"67\",\n" +
                                    "            \"type\": \"MO\",\n" +
                                    "            \"url\": \"https://mywebhook.com/mo\",\n" +
                                    "            \"last_http_code\": 404\n" +
                                    "        }\n" +
                                    "    ]\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void retrieveWebhooks() throws IOException {
        // given
        RetrieveWebhooksResultResponse expectedGetListsResponse = RetrieveWebhooksResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .webhooks(Arrays.asList(
                        Webhook.builder()
                                .id("66")
                                .type(WebhookType.DLR)
                                .url("https://mywebhook.com/dlr")
                                .lastHttpCode(200)
                                .build(),
                        Webhook.builder()
                                .id("67")
                                .type(WebhookType.MO)
                                .url("https://mywebhook.com/mo")
                                .lastHttpCode(404)
                                .build()
                        ))
                .build();


        // when
        final RetrieveWebhooksResultResponse actualGetListsResponse = retrieveWebhooksService.retrieveWebhooks(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualGetListsResponse.getStatus());
        assertEquals("OK", actualGetListsResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualGetListsResponse);

    }
}