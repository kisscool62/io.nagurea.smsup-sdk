package io.nagurea.smsupsdk.lists.clear;

import com.google.gson.Gson;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class ClearListService extends PUTSMSUpService {
    private static final String URL = "/list/%s/npai/clear";
    private static final String ID = "list id";

    protected ClearListService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to add the NPAI of your list into your NPAI list and delete NPAI in this list.
     * @param token
     * @param id The list id
     * @return the status and number of removed items
     * @throws IOException
     */
    public ClearListResponse clear(@NonNull String token, @NonNull String id) throws IOException {
        final ImmutablePair<Integer, String> response = put(buildUrl(id), token);
        final ClearListResultResponse responseObject = new Gson().fromJson(response.getRight(), ClearListResultResponse.class);
        return ClearListResponse.builder()
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
