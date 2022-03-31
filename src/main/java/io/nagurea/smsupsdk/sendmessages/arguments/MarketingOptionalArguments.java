package io.nagurea.smsupsdk.sendmessages.arguments;

import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;

import static io.nagurea.smsupsdk.sendmessages.PushType.MARKETING;

public class MarketingOptionalArguments extends OptionalArguments{


    @Builder
    public MarketingOptionalArguments(String delay, Sender sender, String gsmsmsid) {
        super(MARKETING, delay, sender, gsmsmsid);
    }
}
