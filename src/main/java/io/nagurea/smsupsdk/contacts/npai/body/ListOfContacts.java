package io.nagurea.smsupsdk.contacts.npai.body;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

@Builder
@ToString
public class ListOfContacts {

    private final List<Gsm> gsm;

}
