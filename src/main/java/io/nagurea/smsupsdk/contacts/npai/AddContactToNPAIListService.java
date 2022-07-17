package io.nagurea.smsupsdk.contacts.npai;


import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.contacts.npai.body.ContactListBody;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class AddContactToNPAIListService extends POSTSMSUpService {

    private static final String URL = "/npai";

    public AddContactToNPAIListService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Add contacts to the NPAI list
     * @param token SMSUp token
     * @param contactListBody @{@link ContactListBody} represents npai to add contact to
     * @return AddContactToNPAIListResponse with detailed @{@link AddContactToNPAIListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public AddContactToNPAIListResponse addContactToNPAIList(String token, @NonNull ContactListBody contactListBody) throws IOException {
        final ImmutablePair<Integer, String> response = post(URL, token, GsonHelper.toJson(contactListBody));
        final String body = response.getRight();
        final AddContactToNPAIListResultResponse responseObject = GsonHelper.fromJson(body, AddContactToNPAIListResultResponse.class);
        return AddContactToNPAIListResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
