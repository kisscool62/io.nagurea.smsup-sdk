package io.nagurea.smsupsdk.contacts.insert.body;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class ListOfContacts {

    private final List<Gsm> gsm;

}
