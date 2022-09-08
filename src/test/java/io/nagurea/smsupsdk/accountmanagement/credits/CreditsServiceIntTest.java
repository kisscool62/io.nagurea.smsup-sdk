package io.nagurea.smsupsdk.accountmanagement.credits;

import io.nagurea.smsupsdk.apitoken.retrieve.RetrieveTokensResultResponse;
import io.nagurea.smsupsdk.apitoken.retrieve.TokenInfo;
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
class CreditsServiceIntTest {

    private static final String YOUR_TOKEN = "Your Token";
    private static final String EXPECTED_TOKEN = "Bearer " + YOUR_TOKEN;

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CreditsService creditsService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);
        mockServer.when(
                request()
                        .withPath("/credits")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                            "{\n" +
                                    "  \"status\": 1,\n" +
                                    "  \"credits\": \"318\",\n" +
                                    "  \"postpaid\": \"1\",\n" +
                                    "  \"message\": \"OK\"\n" +
                                    "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void getCredits() throws IOException {
        // given
        CreditsResultResponse creditsResultResponse = CreditsResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .credits("318")
                .postpaid("1")
                .build();


        // when
        final CreditsResultResponse actualResultResponse = creditsService.getCredits(YOUR_TOKEN).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualResultResponse.getStatus());
        assertEquals("OK", actualResultResponse.getMessage());
        assertEquals(creditsResultResponse, actualResultResponse);

    }
}