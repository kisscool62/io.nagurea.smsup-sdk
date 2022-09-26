package io.nagurea.smsupsdk.accountmanaging.subaccount.lock;

import io.nagurea.smsupsdk.accountmanaging.subaccount.lock.response.LockSubaccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.RetrieveSubaccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.RetrieveSubaccountsResultResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.retrieve.response.SubaccountInfo;
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

import static io.nagurea.smsupsdk.accountmanaging.subaccount.lock.LockState.LOCKED;
import static io.nagurea.smsupsdk.accountmanaging.subaccount.lock.LockState.UNLOCKED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)
class LockSubaccountServiceIntTest extends TestIntBase {

    private static final String SUB_ACCCOUNT_ID = "12123";

    /**
     * Useless. Only here to see how services could be used with Spring
     */
    @Autowired
    private LockSubaccountService lockSubaccountService;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startMockSMSUpServer(){
        mockServer = startMockServer();

        mockServer.when(
                request()
                        .withPath("/account/" + SUB_ACCCOUNT_ID + "/lock")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"state\": \"locked\"\n" +
                                        "}"
                        )
        );

        mockServer.when(
                request()
                        .withPath("/account/" + SUB_ACCCOUNT_ID + "/unlock")
                        .withMethod("GET")
                        .withHeader("Authorization", EXPECTED_TOKEN)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(200)
                        .withBody(
                                "{ \n" +
                                        "    \"status\": 1,\n" +
                                        "    \"message\": \"OK\",\n" +
                                        "    \"state\": \"unlocked\"\n" +
                                        "}"
                        )
        );

    }

    @AfterAll
    static void stopMockserver(){
        mockServer.stop();
    }

    @Test
    void lockSubaccount() throws IOException {
        // given
        LockSubaccountResultResponse expectedGetListsResponse = LockSubaccountResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .state(LOCKED)
                .build();


        // when
        final LockSubaccountResultResponse actualAccountResponse = lockSubaccountService.lockSubaccounts(YOUR_TOKEN, SUB_ACCCOUNT_ID).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualAccountResponse.getStatus());
        assertEquals("OK", actualAccountResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualAccountResponse);

    }

    @Test
    void unlockSubaccounts() throws IOException {
        // given
        LockSubaccountResultResponse expectedGetListsResponse = LockSubaccountResultResponse.builder()
                .message("OK")
                .responseStatus(ResponseStatus.OK)
                .state(UNLOCKED)
                .build();


        // when
        final LockSubaccountResultResponse actualAccountResponse = lockSubaccountService.unlockSubaccounts(YOUR_TOKEN, SUB_ACCCOUNT_ID).getEffectiveResponse();

        // then
        assertEquals(ResponseStatus.OK, actualAccountResponse.getStatus());
        assertEquals("OK", actualAccountResponse.getMessage());
        assertEquals(expectedGetListsResponse, actualAccountResponse);

    }


}