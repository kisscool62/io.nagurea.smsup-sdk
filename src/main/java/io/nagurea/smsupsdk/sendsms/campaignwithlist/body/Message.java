package io.nagurea.smsupsdk.sendsms.campaignwithlist.body;

import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
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
