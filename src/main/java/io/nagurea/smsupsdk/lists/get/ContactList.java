package io.nagurea.smsupsdk.lists.get;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class ContactList {
    private final String id;
    private final String name;
    private final LocalDateTime date;
    private final String count;
}
