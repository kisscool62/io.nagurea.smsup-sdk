package io.nagurea.smsupsdk.apitoken.retrieve;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class TokenInfo {
    private final String name;

    @SerializedName("api_token_id")
    private final Integer apiTokenId;

    @SerializedName("api_token")
    private final String apiToken;

    @SerializedName("created_at")
    private final LocalDateTime createdAt;
}
