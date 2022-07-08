package io.nagurea.smsupsdk.campaigns.get.replies;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Reply {
    private final String destination;
    private final String info1;
    private final String info2;
    private final LocalDateTime date;
}
