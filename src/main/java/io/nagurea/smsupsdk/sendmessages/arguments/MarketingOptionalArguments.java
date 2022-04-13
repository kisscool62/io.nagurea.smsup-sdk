package io.nagurea.smsupsdk.sendmessages.arguments;

import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;

import static io.nagurea.smsupsdk.sendmessages.arguments.PushType.MARKETING;

public class MarketingOptionalArguments extends OptionalArguments{


    @Builder
    public MarketingOptionalArguments(final Delay delay, final Sender sender, final String gsmsmsid) {
        super(MARKETING, delay, sender, gsmsmsid);
    }
}
