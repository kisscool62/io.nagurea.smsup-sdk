package io.nagurea.smsupsdk.contacts.deduplicate;

import com.google.gson.Gson;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class DeduplicateService extends PUTSMSUpService {
    private static final String URL = "/list/deduplicate/%s";
    private static final String ID = "list id";

    public DeduplicateService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Deduplicate list
     * @param token
     * @param id The list id
     * @return the status and number of removed items
     * @throws IOException
     */
    public DeduplicateResponse deduplicate(@NonNull String token, @NonNull String id) throws IOException {
        final ImmutablePair<Integer, String> response = put(buildUrl(id), token);
        final DeduplicateResultResponse responseObject = new Gson().fromJson(response.getRight(), DeduplicateResultResponse.class);
        return DeduplicateResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrl(@NonNull String id) {
        final RequiredParameterException.RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format(URL, id);
    }


}
