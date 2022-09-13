package io.nagurea.smsupsdk.apitoken.create.body;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class TokenBody {
    private final TokenInfo token;
}
