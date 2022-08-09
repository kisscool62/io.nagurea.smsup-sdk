package io.nagurea.smsupsdk.apitoken.create.body;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class TokenInfo {

    /**
     * The token name
     */
    private final String name;

    /**
     * The TTL in seconds (default unlimited)
     */
    private final Integer ttl;

}
