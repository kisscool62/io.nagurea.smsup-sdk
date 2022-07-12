package io.nagurea.smsupsdk.contacts.insert.body;

import lombok.Builder;
import lombok.ToString;

/**
 Body of post query described on https://doc.smsup.ch/en/api/sms/contact/add-contact
 */
@Builder
@ToString
public class ContactListBody {

    private final ContactList list;


}
