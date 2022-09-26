package io.nagurea.smsupsdk.accountmanaging.transfercredits;

import io.nagurea.smsupsdk.accountmanaging.account.create.CreateAccountResponse;
import io.nagurea.smsupsdk.accountmanaging.account.create.CreateAccountResultResponse;
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
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class TransferCreditsServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private TransferCreditsService transferCreditsService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object transfer = new Object() {
            public final Integer to_account = 1010101;
            public final Integer credits = 10;
        };
    };

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();

        mockServer.when(
                request()
                        .withPath("/account/transfer")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"credits\": \"990\",\n" +
                                        "  \"child_credits\": \"1010\"\n" +
                                        "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void transferCredits() throws IOException {
         //given
         final TransferCreditsResultResponse expectedResponse = TransferCreditsResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .credits("990")
                 .childCredits("1010")
                 .build();
         final int expectedStatusCode = 200;

         //when
         final TransferCreditsResponse result = transferCreditsService.transferCredits("token", 1010101, 10);
         final Integer effectiveStatusCode = result.getStatusCode();
         final TransferCreditsResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }


}
