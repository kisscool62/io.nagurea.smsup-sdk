package io.nagurea.smsupsdk.hlrlookup.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@EqualsAndHashCode
public class LookupStatus {
    private final String name;
    private final String description;
    private final String detailed;
}
