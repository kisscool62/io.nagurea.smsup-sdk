package io.nagurea.smsupsdk.notifications.balance.get;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Notification {

    @SerializedName("alert_email")
    private final String alertEmail;

    @SerializedName("alert_gsm")
    private final String alertGsm;

    private final String email;

    @SerializedName("phone_number")
    private final String phoneNumber;

    @SerializedName("alert_trigger")
    private final String alertTrigger;


}
