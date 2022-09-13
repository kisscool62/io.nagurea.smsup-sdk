package io.nagurea.smsupsdk.accountmanaging.account.create.body;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class CreateAccountBody {

    @NotNull
    private final AccountInfo account;
}
