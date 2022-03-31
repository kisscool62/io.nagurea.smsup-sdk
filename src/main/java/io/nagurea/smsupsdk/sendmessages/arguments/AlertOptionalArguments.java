package io.nagurea.smsupsdk.sendmessages.arguments;

import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;

import static io.nagurea.smsupsdk.sendmessages.PushType.ALERT;

public class AlertOptionalArguments extends OptionalArguments{

    @Builder
    public AlertOptionalArguments(String delay, Sender sender, String gsmsmsid) {
        super(ALERT, delay, sender, gsmsmsid);
    }
}
