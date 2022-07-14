package io.nagurea.smsupsdk.notifications.balance.update.body.request;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.notifications.balance.update.body.ActivationStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Notification {

    @SerializedName("alert_trigger")
    private final String alertTrigger;

    @SerializedName("alert_email")
    private final ActivationStatus alertEmail;

    @SerializedName("alert_gsm")
    private final ActivationStatus alertGsm;

    private final String email;

    @SerializedName("phone_number")
    private final String phoneNumber;


}
