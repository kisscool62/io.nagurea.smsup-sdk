package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class RetentionTime {

    @NotNull
    @Min(1)
    private final Integer number;

    @NonNull
    private final TimeUnit timeUnit;

    @Override
    public String toString() {
        return number + timeUnit.getLabel();
    }


}
