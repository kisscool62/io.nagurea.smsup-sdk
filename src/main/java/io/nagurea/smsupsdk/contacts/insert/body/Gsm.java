package io.nagurea.smsupsdk.contacts.insert.body;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Gsm {

    private final String value;
    private final String info1;
    private final String info2;
    private final String info3;
    private final String info4;

}
