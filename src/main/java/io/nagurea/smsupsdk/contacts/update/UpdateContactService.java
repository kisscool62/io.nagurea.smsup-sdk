package io.nagurea.smsupsdk.contacts.update;

import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.http.put.PUTSMSUpService;
import io.nagurea.smsupsdk.contacts.update.body.ContactBody;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class UpdateContactService extends PUTSMSUpService {
    private static final String URL = "/list/contact/%s";
    private static final String ID = "contact id";

    public UpdateContactService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Based on https://doc.smsup.ch/en/api/sms/contact/update
     * Be careful. Spec says PUT /list/contact/:id but example says PUT /list
     * This method allows you to update some contacts of one of your list.
     * @param token
     * @param id The contact id
     * @return the status and message
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public UpdateContactResponse updateContact(@NonNull String token, @NonNull String id, @NonNull ContactBody contactBody) throws IOException {
        final ImmutablePair<Integer, String> response = put(buildUrl(id), token, GsonHelper.toJson(contactBody));
        final UpdateContactResultResponse responseObject = GsonHelper.fromJson(response.getRight(), UpdateContactResultResponse.class);
        return UpdateContactResponse.builder()
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
