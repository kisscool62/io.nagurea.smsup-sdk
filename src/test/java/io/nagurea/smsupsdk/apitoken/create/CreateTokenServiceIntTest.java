package io.nagurea.smsupsdk.apitoken.create;

import io.nagurea.smsupsdk.apitoken.create.body.TokenInfo;
import io.nagurea.smsupsdk.common.TestIntBase;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import io.nagurea.smsupsdk.contacts.blacklist.AddContactToBlacklistResponse;
import io.nagurea.smsupsdk.contacts.blacklist.AddContactToBlacklistResultResponse;
import io.nagurea.smsupsdk.contacts.blacklist.AddContactToBlacklistService;
import io.nagurea.smsupsdk.contacts.blacklist.body.Blacklist;
import io.nagurea.smsupsdk.contacts.blacklist.body.ContactListBody;
import io.nagurea.smsupsdk.contacts.blacklist.body.Gsm;
import io.nagurea.smsupsdk.contacts.blacklist.body.ListOfContacts;
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
import static org.mockserver.model.JsonBody.json;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class CreateTokenServiceIntTest extends TestIntBase {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CreateTokenService createTokenService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object token = new Object() {
            public final String name = "Best token ever";
            public final Integer ttl = 2678400;

        };
    };

    private static final Object EXPECTED_JSON_OBJECT_FOR_SUBACCOUNT = new Object() {
        public final Object token = new Object() {
            public final String name = "Best token ever";
        };
    };


    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();

        mockServer.when(
                request()
                        .withPath("/token")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"token\": \"yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMDg2NX0.ZnGgbDC0OI3hPm2UXyl4rxU9JlpMTMBcTJT8RVgJbtQ\",\n" +
                                        "  \"token_id\": \"2\"\n" +
                                        "}"
                        )
        );

        mockServer.when(
                request()
                        .withPath("/token/account/12542")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT_FOR_SUBACCOUNT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"token\": \"yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMDg2NX0.ZnGgbDC0OI3hPm2UXyl4rxU9JlpMTMBcTJT8RVgJbtQ\",\n" +
                                        "  \"token_id\": \"2\"\n" +
                                        "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void createToken() throws IOException {
         //given
         final CreateTokenResultResponse expectedResponse = CreateTokenResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .token("yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMDg2NX0.ZnGgbDC0OI3hPm2UXyl4rxU9JlpMTMBcTJT8RVgJbtQ")
                 .tokenId("2")
                 .build();
         final int expectedStatusCode = 200;

         final TokenInfo tokenInfo = TokenInfo.builder()
                 .name("Best token ever")
                 .ttl(2678400)
                 .build();

         //when
         final CreateTokenResponse result = createTokenService.createToken("token", tokenInfo);
         final Integer effectiveStatusCode = result.getStatusCode();
         final CreateTokenResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }

    @Test
    void createTokenForASubaccount() throws IOException {
        //given
        final CreateTokenResultResponse expectedResponse = CreateTokenResultResponse.builder()
                .message(ResponseStatus.OK.getDescription())
                .responseStatus(ResponseStatus.OK)
                .token("yJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTY1NiIsImlhdCI6MTUxOTEyMDg2NX0.ZnGgbDC0OI3hPm2UXyl4rxU9JlpMTMBcTJT8RVgJbtQ")
                .tokenId("2")
                .build();
        final int expectedStatusCode = 200;

        final TokenInfo tokenInfo = TokenInfo.builder()
                .name("Best token ever")
                .build();

        //when
        final CreateTokenResponse result = createTokenService.createTokenForASubaccount("token", "12542", tokenInfo);
        final Integer effectiveStatusCode = result.getStatusCode();
        final CreateTokenResultResponse effectiveResponse = result.getEffectiveResponse();

        //then
        assertEquals(expectedStatusCode, effectiveStatusCode);
        assertEquals(expectedResponse, effectiveResponse);
        assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
        assertEquals("OK", effectiveResponse.getMessage());

    }

}
