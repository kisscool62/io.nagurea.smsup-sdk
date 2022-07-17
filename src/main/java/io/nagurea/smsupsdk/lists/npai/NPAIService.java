package io.nagurea.smsupsdk.lists.npai;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class NPAIService extends GETSMSUpService {
    private static final String URL = "/npai";

    public NPAIService(String rootUrl) {
        super(rootUrl);
    }

    public NPAIResponse getNPAIList(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final NPAIResultResponse responseObject = GsonHelper.fromJson(body, NPAIResultResponse.class);
        return NPAIResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
