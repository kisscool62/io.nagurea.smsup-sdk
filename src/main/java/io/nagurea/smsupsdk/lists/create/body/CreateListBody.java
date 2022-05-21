package io.nagurea.smsupsdk.lists.create.body;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateListBody {
    private final ContactList list;
}
