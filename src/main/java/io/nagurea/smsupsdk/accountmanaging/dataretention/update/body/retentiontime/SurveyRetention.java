package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.RetentionTime;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.ValidRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.ValidRetentionTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import static io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit.DAY;
import static io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit.MONTH;

@EqualsAndHashCode(callSuper = true)
@ValidRetentionTime
public class SurveyRetention extends RetentionTime implements ValidRetention {

    @Builder
    public SurveyRetention(@NonNull Integer number, @NonNull TimeUnit timeUnit) {
        super(number, timeUnit);
    }

    @Override
    public boolean isValid() {
        return DAY.equals(getTimeUnit()) ||
                (MONTH.equals(getTimeUnit()) && getNumber() <= 26);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
