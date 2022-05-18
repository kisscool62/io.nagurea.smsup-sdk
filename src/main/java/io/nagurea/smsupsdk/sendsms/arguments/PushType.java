package io.nagurea.smsupsdk.sendsms.arguments;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PushType {
    ALERT("alert"),
    MARKETING("marketing");

    @Getter
    private final String label;

}
