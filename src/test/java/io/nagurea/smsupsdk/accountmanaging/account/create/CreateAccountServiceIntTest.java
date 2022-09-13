package io.nagurea.smsupsdk.accountmanaging.account.create;

import com.neovisionaries.i18n.CountryCode;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.AccountInfo;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.Childness;
import io.nagurea.smsupsdk.accountmanaging.account.create.body.CreateAccountBody;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.NonNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.matchers.MatchType;
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
class CreateAccountServiceIntTest {

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private CreateAccountService createAccountService;

    private static ClientAndServer mockServer;

    private static final Object EXPECTED_JSON_OBJECT = new Object() {
        public final Object account = new Object() {
            public final String email = "vasili@sovietnavy.com";
            public final String password = "av01d_nuc13Ar_War";
            public final String firstname = "Vasili";
            public final String lastname = "Arkhipov";
            public final String city = "Zvorkovo";
            public final String phone = "41781234567";
            public final String address1 = "Somewhere in Zvorkovo";
            public final String zip = "386";
            public final String country_code = "ru";
            public final Integer isChild = 1;
            public final Integer unlimited = 0;
        };
    };

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = ClientAndServer.startClientAndServer("localhost", 4242, 4242);

        mockServer.when(
                request()
                        .withPath("/account")
                        .withMethod("POST")
                        .withBody(json(EXPECTED_JSON_OBJECT))
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{\n" +
                                        "  \"status\": 1,\n" +
                                        "  \"message\": \"OK\",\n" +
                                        "  \"id\": \"11689\",\n" +
                                        "  \"active\": \"1\"\n" +
                                        "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

     @Test
     void createAccount() throws IOException {
         //given
         final CreateAccountResultResponse expectedResponse = CreateAccountResultResponse.builder()
                 .message(ResponseStatus.OK.getDescription())
                 .responseStatus(ResponseStatus.OK)
                 .id("11689")
                 .active("1")
                 .build();
         final int expectedStatusCode = 200;

         final AccountInfo accountInfo = AccountInfo.builder()
                 .email("vasili@sovietnavy.com")
                 .password("av01d_nuc13Ar_War")
                 .firstname("Vasili")
                 .lastname("Arkhipov")
                 .city("Zvorkovo")
                 .phone("41781234567")
                 .address1("Somewhere in Zvorkovo")
                 .zip("386")
                 .countryCode(CountryCode.RU)
                 .isChild(Childness.SUB_ACCOUNT)
                 .unlimited(0)
                 .build();

         final CreateAccountBody createAccountBody = CreateAccountBody.builder()
                 .account(accountInfo)
                 .build();

         //when
         final CreateAccountResponse result = createAccountService.createAccount("token", createAccountBody);
         final Integer effectiveStatusCode = result.getStatusCode();
         final CreateAccountResultResponse effectiveResponse = result.getEffectiveResponse();

         //then
         assertEquals(expectedStatusCode, effectiveStatusCode);
         assertEquals(expectedResponse, effectiveResponse);
         assertEquals(ResponseStatus.OK, effectiveResponse.getStatus());
         assertEquals("OK", effectiveResponse.getMessage());

    }


}
