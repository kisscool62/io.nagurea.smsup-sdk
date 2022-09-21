package io.nagurea.smsupsdk.accountmanaging.subaccount.lock;

import java.util.Locale;

public enum LockState {
    LOCKED,
    UNLOCKED;

    public static LockState findByName(String name) {
        final LockState[] values = values();
        for (LockState value : values) {
            if(value.name().equals(name.toUpperCase(Locale.ROOT))){
                return value;
            }
        }
        return null;
    }

}
