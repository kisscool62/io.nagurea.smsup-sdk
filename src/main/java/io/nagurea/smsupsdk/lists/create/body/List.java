package io.nagurea.smsupsdk.lists.create.body;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class List {
    private final String name;
    private final Contacts contacts;
}
