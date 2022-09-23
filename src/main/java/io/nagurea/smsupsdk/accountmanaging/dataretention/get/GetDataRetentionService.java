package io.nagurea.smsupsdk.accountmanaging.dataretention.get;

import io.nagurea.smsupsdk.accountmanaging.dataretention.get.response.DataRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.get.response.GetDataRetentionResponse;
import io.nagurea.smsupsdk.accountmanaging.dataretention.get.response.GetDataRetentionResultResponse;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class GetDataRetentionService extends GETSMSUpService {
    private static final String URL_DATA_RETENTION = "/retention";

    public GetDataRetentionService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to get the values of your data retention.
     * After a number the "d" matches with day and the "m" matches with month
     * @param token
     * @return @{@link GetDataRetentionResponse} with data retention info (@{@link DataRetention})
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public GetDataRetentionResponse getDataRetention(String token) throws IOException {
        final ImmutablePair<Integer, String> response = get(URL_DATA_RETENTION, token);
        final String body = response.getRight();
        final GetDataRetentionResultResponse responseObject = GsonHelper.fromJson(body, GetDataRetentionResultResponse.class);
        return GetDataRetentionResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
