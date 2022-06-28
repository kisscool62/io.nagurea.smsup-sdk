package io.nagurea.smsupsdk.sendsms.arguments;

import io.nagurea.smsupsdk.sendsms.sender.Sender;
import lombok.Builder;
import lombok.Getter;

import static io.nagurea.smsupsdk.sendsms.arguments.PushType.MARKETING;

@Getter
public class MarketingOptionalArguments extends OptionalArguments{

    @Builder
    public MarketingOptionalArguments(final Delay delay, final Sender sender, final String gsmsmsid, int unicode) {
        super(MARKETING, delay, sender, gsmsmsid, unicode);
    }

}
