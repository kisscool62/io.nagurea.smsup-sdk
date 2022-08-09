package io.nagurea.smsupsdk.apitoken.create;


import io.nagurea.smsupsdk.apitoken.create.body.TokenInfo;
import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.URLHelper;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class CreateTokenService extends POSTSMSUpService {

    private static final String URL_TOKEN = "/token";
    private static final String URL_TOKEN_SUB_ACCOUNT = "/token/account/";

    public CreateTokenService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * API Tokens are a great way to authenticate to our API while being separate from your username and password. We strongly recommend you to use them. You'll find here how to manage your API tokens. You can create a token with our API or on our platform in the Developer menu.
     * @param token SMSUp token
     * @param tokenInfo @{@link TokenInfo} represents token info to create the token (name + ttl)
     * @return CreateTokenResponse with detailed @{@link CreateTokenResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CreateTokenResponse createToken(String token, @NonNull TokenInfo tokenInfo) throws IOException {
        final ImmutablePair<Integer, String> response = post(URL_TOKEN, token, GsonHelper.toJson(tokenInfo));
        final String body = response.getRight();
        final CreateTokenResultResponse responseObject = GsonHelper.fromJson(body, CreateTokenResultResponse.class);
        return CreateTokenResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    /**
     * API Tokens are a great way to authenticate to our API while being separate from your username and password. We strongly recommend you to use them. You'll find here how to manage your API tokens. You can create a token with our API or on our platform in the Developer menu.
     * @param token SMSUp token
     * @param accountId The account ID you wish to create a token for
     * @param tokenInfo @{@link TokenInfo} represents token info to create the token (name + ttl)
     * @return CreateTokenResponse with detailed @{@link CreateTokenResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CreateTokenResponse createTokenForASubaccount(String token, @NonNull String accountId, @NonNull TokenInfo tokenInfo) throws IOException {
        final ImmutablePair<Integer, String> response = post(buildUrl(URL_TOKEN_SUB_ACCOUNT, accountId), token, GsonHelper.toJson(tokenInfo));
        final String body = response.getRight();
        final CreateTokenResultResponse responseObject = GsonHelper.fromJson(body, CreateTokenResultResponse.class);
        return CreateTokenResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrl(String urlTokenSubAccount, String accountId) {
        return URLHelper.add(URL_TOKEN_SUB_ACCOUNT, accountId);
    }


}
