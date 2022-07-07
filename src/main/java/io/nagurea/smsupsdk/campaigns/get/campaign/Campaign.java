package io.nagurea.smsupsdk.campaigns.get.campaign;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

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
    private final List<ListId> lists;
    private final String delivered;
    private final String error;
    private final String expired;

    @SerializedName("network_error")
    private final String networkError;
    private final String stop;
    private final String npai;
}
