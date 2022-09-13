package io.nagurea.smsupsdk.accountmanaging.account.create.body;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    COMPANY("company"),
    ASSOCIATION("association"),
    ADMINISTRATION("administration"),
    PRIVATE("private");

    private final String label;
}
