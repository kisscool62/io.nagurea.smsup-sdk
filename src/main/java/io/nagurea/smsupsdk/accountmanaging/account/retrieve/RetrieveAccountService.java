package io.nagurea.smsupsdk.accountmanaging.account.retrieve;

import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account.AccountInfo;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account.RetrieveAccountResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account.RetrieveAccountResultResponse;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class RetrieveAccountService extends GETSMSUpService {
    private static final String URL_ACCOUNT = "/account";

    public RetrieveAccountService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Retrieve account linked to the given token
     * @param token
     * @return @{@link RetrieveAccountResponse} with account (@{@link AccountInfo})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RetrieveAccountResponse retrieveAccount(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL_ACCOUNT, token);
        final String body = response.getRight();
        final RetrieveAccountResultResponse responseObject = GsonHelper.fromJson(body, RetrieveAccountResultResponse.class);
        return RetrieveAccountResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
