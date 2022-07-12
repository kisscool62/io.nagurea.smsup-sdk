package io.nagurea.smsupsdk.contacts.remove;


import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.delete.DELETESMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class RemoveContactService extends DELETESMSUpService {

    private static final String URL = "/list/contact";
    private static final String ID = "contact id";

    protected RemoveContactService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to remove a contact from a list. You can retrieve a contact id by first retrieving the associated list.
     * @param token SMSUp token
     * @param id The contact id
     * @return RemoveContactResponse with detailed @{@link RemoveContactResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public RemoveContactResponse removeContact(String token, String id) throws IOException {
        final ImmutablePair<Integer, String> response = delete(buildSendUrl(id), token);
        final String body = response.getRight();
        final RemoveContactResultResponse responseObject = GsonHelper.fromJson(body, RemoveContactResultResponse.class);
        return RemoveContactResponse.builder()
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

        return String.format("%s/%s", RemoveContactService.URL, id);
    }

}
