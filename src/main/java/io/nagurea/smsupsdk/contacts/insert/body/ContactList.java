package io.nagurea.smsupsdk.contacts.insert.body;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ContactList {

    private final Integer listId;
    private final ListOfContacts contacts;


}
