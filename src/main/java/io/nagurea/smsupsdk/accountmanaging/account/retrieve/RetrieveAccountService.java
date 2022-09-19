package io.nagurea.smsupsdk.accountmanaging.account.retrieve;

import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account.AccountInfo;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account.RetrieveAccountResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account.RetrieveAccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.subaccount.RetrieveSubaccountResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.subaccount.RetrieveSubaccountResultResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.subaccount.RetrieveSubaccountsResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.subaccount.RetrieveSubaccountsResultResponse;
import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.subaccount.SubaccountInfo;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class RetrieveAccountService extends GETSMSUpService {
    private static final String URL_ACCOUNT = "/account";
    private static final String URL_SUBACCOUNTS = "/sub-accounts";
    private static final String URL_SUBACCOUNT = "/sub-account/%s";

    private static final String ID = "id";

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

    /**
     * Retrieve sub-account linked to the given token and to the id
     * @param token
     * @param id of the sub-account to retrieve
     * @return @{@link RetrieveSubaccountResponse} with sub-account (@{@link SubaccountInfo})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RetrieveSubaccountResponse retrieveSubaccount(String token, @NonNull String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(id), token);
        final String body = response.getRight();
        final RetrieveSubaccountResultResponse responseObject = GsonHelper.fromJson(body, RetrieveSubaccountResultResponse.class);
        return RetrieveSubaccountResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    /**
     * Retrieve sub-accounts linked to the given token
     * @param token
     * @return @{@link RetrieveSubaccountsResponse} with sub-account (@{@link SubaccountInfo})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RetrieveSubaccountsResponse retrieveSubaccounts(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL_SUBACCOUNTS, token);
        final String body = response.getRight();
        final RetrieveSubaccountsResultResponse responseObject = GsonHelper.fromJson(body, RetrieveSubaccountsResultResponse.class);
        return RetrieveSubaccountsResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildSendUrl(@NonNull String id) {
        final RequiredParameterException.RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format(URL_SUBACCOUNT, id);
    }

}
