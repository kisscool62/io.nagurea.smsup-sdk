package io.nagurea.smsupsdk.campaigns.get.history;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Campaign {
    private final String id;
    private final String sender;
    private final String text;
    private final LocalDateTime date;
    private final String cost;

    @SerializedName("delivery_rate")
    private final String deliveryRate;

}
