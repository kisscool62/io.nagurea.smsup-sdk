package io.nagurea.smsupsdk.apitoken.retrieve;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class RetrieveTokensService extends GETSMSUpService {
    private static final String URL = "/token";

    public RetrieveTokensService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Retrieve every tokens available for the given token
     * @param token
     * @return @{@link RetrieveTokensResponse} with List of tokens (@{@link TokenInfo})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RetrieveTokensResponse retrieveTokens(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final RetrieveTokensResultResponse responseObject = GsonHelper.fromJson(body, RetrieveTokensResultResponse.class);
        return RetrieveTokensResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
