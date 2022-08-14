package io.nagurea.smsupsdk.hlrlookup.body;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class LookupBody {

    @Singular("to")
    private final List<String> to;
}
