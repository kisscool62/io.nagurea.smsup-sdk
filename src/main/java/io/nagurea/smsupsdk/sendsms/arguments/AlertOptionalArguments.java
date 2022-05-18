package io.nagurea.smsupsdk.sendsms.arguments;

import io.nagurea.smsupsdk.sendsms.sender.Sender;
import lombok.Builder;

public class AlertOptionalArguments extends OptionalArguments{

    @Builder
    public AlertOptionalArguments(Delay delay, Sender sender, String gsmsmsid, int unicode) {
        super(PushType.ALERT, delay, sender, gsmsmsid, unicode);
    }

}
