package io.nagurea.smsupsdk.lists.create;

import com.google.gson.Gson;
import io.nagurea.smsupsdk.common.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.lists.create.arguments.CreateListArguments;
import io.nagurea.smsupsdk.lists.create.body.CreateListBody;
import io.nagurea.smsupsdk.lists.create.body.ContactList;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class CreateListService extends POSTSMSUpService {
    private static final String URL = "/list";

    protected CreateListService(String rootUrl) {
        super(rootUrl);
    }

    public CreateListResponse create(@NonNull String token, @NonNull CreateListArguments createListArguments) throws IOException {
        createListArguments.checkContacts();
        final ImmutablePair<Integer, String> response = post(URL, token, buildData(createListArguments));
        final CreateListResultResponse responseObject = new Gson().fromJson(response.getRight(), CreateListResultResponse.class);
        return CreateListResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildData(CreateListArguments createListArguments) {

        final CreateListBody createListBody = CreateListBody.builder()
                .list(
                        ContactList.builder()
                                .name(createListArguments.getName())
                                .contacts(createListArguments.getContacts())
                                .build())
                .build();
        return GsonHelper.toJson(createListBody);
    }


}
