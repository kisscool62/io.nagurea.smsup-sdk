package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class DataRetentionBody {

    @Valid
    private final DataRetentionInfo retention;

    public boolean isValid(){
        return retention.isValid();
    }

}
