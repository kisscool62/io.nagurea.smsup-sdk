package io.nagurea.smsupsdk.hlrlookup.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@EqualsAndHashCode
public class LookupError {
    private final Integer id;
    private final String description;
    private final Boolean permanent;
}
