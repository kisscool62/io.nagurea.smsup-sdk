package io.nagurea.smsupsdk.lists.delete;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.delete.DELETESMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class DeleteListService extends DELETESMSUpService {

    private static final String URL = "/list";
    private static final String ID = "list id";

    protected DeleteListService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to delete a list of contacts.
     * @param token SMSUp token
     * @param id The list id
     * @return DeleteListResponse with detailed @DeleteListResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public DeleteListResponse deleteList(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = delete(buildSendUrl(id), token);
        final String body = response.getRight();
        final DeleteListResultResponse responseObject = GsonHelper.fromJson(body, DeleteListResultResponse.class);
        return DeleteListResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }



    private String buildSendUrl(@NonNull String id) {
        final RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(StringUtils.isBlank(id)){
            exception.requiredParam(ID, id);
            throw exception.build();
        }

        return String.format("%s/%s", DeleteListService.URL, id);
    }

}
