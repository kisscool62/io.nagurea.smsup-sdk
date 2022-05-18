package io.nagurea.smsupsdk.lists.create.arguments;

import io.nagurea.smsupsdk.lists.create.body.Contacts;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class CreateListArguments {

    /**
     * Ex: My list
     */
    private final String name;

    /**
     * List of contacts represented by their phone numbers with up to 4 additional infos
     */
    private final @NonNull Contacts contacts;

    public void checkContacts(){
        contacts.check();
    }
}
