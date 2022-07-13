package io.nagurea.smsupsdk.contacts.update.body;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class ContactBody {

    private final String destination;
    private final String info1;
    private final String info2;
    private final String info3;
    private final String info4;


}
