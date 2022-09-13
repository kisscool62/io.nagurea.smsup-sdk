package io.nagurea.smsupsdk.hlrlookup.body;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LookupBody {
    private final LookupInfo lookup;
}
