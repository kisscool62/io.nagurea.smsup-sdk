package io.nagurea.smsupsdk.sendmessages.campaign.body;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Campaign {
    private final SMS sms;
}

