package io.nagurea.smsupsdk.sendmessages.arguments;

import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;

public class AlertOptionalArguments extends OptionalArguments{

    @Builder
    public AlertOptionalArguments(Delay delay, Sender sender, String gsmsmsid) {
        super(PushType.ALERT, delay, sender, gsmsmsid);
    }

}
