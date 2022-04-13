package io.nagurea.smsupsdk.sendmessages.campaign.body;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SMS {
    private final Message message;
    private final Recipients recipients;
}
