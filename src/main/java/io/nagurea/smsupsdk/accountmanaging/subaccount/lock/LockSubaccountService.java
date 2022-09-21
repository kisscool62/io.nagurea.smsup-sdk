package io.nagurea.smsupsdk.accountmanaging.subaccount.lock;

import io.nagurea.smsupsdk.accountmanaging.subaccount.lock.response.LockSubaccountResponse;
import io.nagurea.smsupsdk.accountmanaging.subaccount.lock.response.LockSubaccountResultResponse;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class LockSubaccountService extends GETSMSUpService {
    private static final String URL_LOCK = "/account/%s/lock";
    private static final String URL_UNLOCK = "/account/%s/unlock";

    private static final String ID = "id";

    public LockSubaccountService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Lock sub-account by sub-account id
     * @param token
     * @return @{@link LockSubaccountResponse} with lock state
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public LockSubaccountResponse lockSubaccounts(String token, String id) throws IOException {
        return getLockSubaccountResponse(token, id, URL_LOCK);
    }

    /**
     * Unlock sub-account by sub-account id
     * @param token
     * @return @{@link LockSubaccountResponse} with lock state
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public LockSubaccountResponse unlockSubaccounts(String token, String id) throws IOException {
        return getLockSubaccountResponse(token, id, URL_UNLOCK);
    }

    private LockSubaccountResponse getLockSubaccountResponse(String token, String id, String urlLock) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(urlLock, id), token);
        final String body = response.getRight();
        final LockSubaccountResultResponse responseObject = GsonHelper.fromJson(body, LockSubaccountResultResponse.class);
        return LockSubaccountResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildSendUrl(@NonNull String url, @NonNull String id) {
        final RequiredParameterException.RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format(url, id);
    }

}
