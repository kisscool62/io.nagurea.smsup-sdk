package io.nagurea.smsupsdk.campaigns.get.stops;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class Stops {
    private final String destination;
    private final String info1;
    private final String info2;
    private final String info3;
    private final String info4;
    private final LocalDateTime date;
}
