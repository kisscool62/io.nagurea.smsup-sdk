package io.nagurea.smsupsdk.contacts.insert;


import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.contacts.insert.body.ContactListBody;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class InsertContactInListService extends POSTSMSUpService {

    private static final String URL = "/list";

    protected InsertContactInListService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * This method allows you to add some contacts to one of your list.
     * @param token SMSUp token
     * @param contactListBody @ContactListBody of the campaign to blacklist the npai
     * @return InsertContactInListResponse with detailed @{@link InsertContactInListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public InsertContactInListResponse insertContactInList(String token, @NonNull ContactListBody contactListBody) throws IOException {
        final ImmutablePair<Integer, String> response = post(URL, token, GsonHelper.toJson(contactListBody));
        final String body = response.getRight();
        final InsertContactInListResultResponse responseObject = GsonHelper.fromJson(body, InsertContactInListResultResponse.class);
        return InsertContactInListResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }


}
