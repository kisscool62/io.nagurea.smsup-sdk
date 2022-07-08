package io.nagurea.smsupsdk.campaigns.get.npais;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode
@ToString
public class NPAI {
    private final String destination;
    private final String info1;
    private final String info2;
    private final String info3;
    private final String info4;
    private final LocalDateTime date;
}
