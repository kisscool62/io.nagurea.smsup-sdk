package io.nagurea.smsupsdk.lists.blacklist;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class BlacklistService extends GETSMSUpService {
    private static final String URL = "/blacklist";

    protected BlacklistService(String rootUrl) {
        super(rootUrl);
    }

    public BlacklistResponse getBlacklist(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final BlackistResultResponse responseObject = GsonHelper.fromJson(body, BlackistResultResponse.class);
        return BlacklistResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
