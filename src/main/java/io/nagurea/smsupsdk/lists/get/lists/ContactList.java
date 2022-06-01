package io.nagurea.smsupsdk.lists.get.lists;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class ContactList {
    private final String id;
    private final String name;
    private final LocalDateTime date;
    private final String count;
}
