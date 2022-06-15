package io.nagurea.smsupsdk.lists.get.list;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.URLHelper;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class GetListService extends GETSMSUpService {
    private static final String URL = "/list";

    protected GetListService(String rootUrl) {
        super(rootUrl);
    }

    public GetListResponse getListById(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildUrlById(id), token);
        final String body = response.getRight();
        final GetListResultResponse responseObject = GsonHelper.fromJson(body, GetListResultResponse.class);
        return GetListResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrlById(String id) {
        return URLHelper.add(URL, id);
    }
}
