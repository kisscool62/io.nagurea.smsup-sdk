package io.nagurea.smsupsdk.sendmessages.campaign.body;

import io.nagurea.smsupsdk.sendmessages.arguments.Delay;
import io.nagurea.smsupsdk.sendmessages.arguments.PushType;
import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Message {
    private final String text;
    private final PushType pushtype;
    private final Sender sender;
    private final String delay;
    private final Integer unicode;

    public static class MessageBuilder {
        public MessageBuilder delay(Delay delay){
            if(delay != null){
                this.delay = delay.getValue();
            }
            return this;
        }
    }
}
