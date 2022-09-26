package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TimeUnit {

    MONTH("m"),
    DAY("d");

    private final String label;
}
