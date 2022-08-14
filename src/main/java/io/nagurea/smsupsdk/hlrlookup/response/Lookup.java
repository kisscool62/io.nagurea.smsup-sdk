package io.nagurea.smsupsdk.hlrlookup.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@EqualsAndHashCode
public class Lookup {
    private final String to;
    private final String mccMnc;
    private final String imsi;
    private final OriginalNetwork originalNetwork;
    private final Boolean ported;
    private final Boolean roaming;
    private final LookupStatus status;
    private final LookupError error;
}
