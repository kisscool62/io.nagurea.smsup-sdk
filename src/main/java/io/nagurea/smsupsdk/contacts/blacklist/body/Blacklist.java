package io.nagurea.smsupsdk.contacts.blacklist.body;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Blacklist {

    private final ListOfContacts contacts;

}
