package io.nagurea.smsupsdk.lists.get.lists;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class GetListsService extends GETSMSUpService {
    private static final String URL = "/lists";

    public GetListsService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to retrieve your lists.
     * @param token
     * @return your lists of contacts
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetListsResponse getLists(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL, token);
        final String body = response.getRight();
        final GetListsResultResponse responseObject = GsonHelper.fromJson(body, GetListsResultResponse.class);
        return GetListsResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
