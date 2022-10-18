package io.nagurea.smsupsdk.apierrors.apierror;

import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

import static io.nagurea.smsupsdk.helper.URLHelper.buildUrl;

public class GetAPIErrorService extends GETSMSUpService {
    private static final String URL = "/errors/%s";

    public GetAPIErrorService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Retrieve every tokens available for the given token
     * @param token
     * @param id The error log id
     * @return @{@link GetAPIErrorResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetAPIErrorResponse getAPIError(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildUrl(URL, id), token);
        final String body = response.getRight();
        final GetAPIErrorResultResponse responseObject = GsonHelper.fromJson(body, GetAPIErrorResultResponse.class);
        return GetAPIErrorResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
