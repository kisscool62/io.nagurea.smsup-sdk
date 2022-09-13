package io.nagurea.smsupsdk.accountmanaging.account.create.body;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Childness {
    MAIN(0),
    SUB_ACCOUNT(1);

    private final Integer value;
}
