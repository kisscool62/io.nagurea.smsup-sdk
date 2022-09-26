package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.EndlessRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.ValidRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.ValidRetentionTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ValidRetentionTime
public class ListRetention implements ValidRetention {

    private final ListRetentionWithExpiration retention;
    private final EndlessRetention endlessRetention;

    public static ListRetention buildListRetention(ListRetentionWithExpiration retention){
        return new ListRetention(retention, null);
    }

    public static ListRetention buildEndlessRetention(){
        return new ListRetention(null, new EndlessRetention());
    }

    public boolean isEndlessRetention(){
        return endlessRetention != null;
    }

    @Override
    public boolean isValid() {
        return isEndlessRetention() || retention.isValid();
    }

    @Override
    public String toString(){
        return isEndlessRetention() ? endlessRetention.toString() : retention.toString();
    }

}
