package io.nagurea.smsupsdk.contacts.blacklist.body;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Gsm {

    private final String value; //The recipient's number
    private final String info1; //Information 1
    private final String info2; //Information 2
    private final String info3; //Information 3
    private final String info4; //Information 4

}
