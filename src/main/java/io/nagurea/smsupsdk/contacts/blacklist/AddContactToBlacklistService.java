package io.nagurea.smsupsdk.contacts.blacklist;


import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.contacts.blacklist.body.ContactListBody;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class AddContactToBlacklistService extends POSTSMSUpService {

    private static final String URL = "/blacklist";

    protected AddContactToBlacklistService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Add contacts to the blacklist
     * @param token SMSUp token
     * @param contactListBody @{@link ContactListBody} represents blacklist to add contact to
     * @return AddContactToBlacklistResponse with detailed @{@link AddContactToBlacklistResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public AddContactToBlacklistResponse addContactToBlacklist(String token, @NonNull ContactListBody contactListBody) throws IOException {
        final ImmutablePair<Integer, String> response = post(URL, token, GsonHelper.toJson(contactListBody));
        final String body = response.getRight();
        final AddContactToBlacklistResultResponse responseObject = GsonHelper.fromJson(body, AddContactToBlacklistResultResponse.class);
        return AddContactToBlacklistResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
