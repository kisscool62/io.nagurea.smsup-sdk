package io.nagurea.smsupsdk.hlrlookup.response;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
@EqualsAndHashCode
public class HLRLookup {

    @Singular
    @SerializedName("lookup")
    private final List<Lookup> lookups;
}
