package io.nagurea.smsupsdk.hlrlookup.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@EqualsAndHashCode
public class OriginalNetwork {
    private final String networkName;
    private final String networkPrefix;
    private final String countryName;
    private final String countryPrefix;
}
