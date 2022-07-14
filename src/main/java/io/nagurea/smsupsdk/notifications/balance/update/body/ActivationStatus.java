package io.nagurea.smsupsdk.notifications.balance.update.body;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivationStatus {

    ACTIVATED("1"),
    DEACTIVATED("0");

    private final String label;

    public static ActivationStatus findByLabel(String label) {
        final ActivationStatus[] values = values();
        for (ActivationStatus value : values) {
            if(value.label.equals(label)){
                return value;
            }
        }
        return null;
    }

}
