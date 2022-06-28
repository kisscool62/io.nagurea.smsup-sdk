package io.nagurea.smsupsdk.sendsms.campaignwithlist.body;

import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageWithList {
    private final String text;
    private final PushType pushtype;
    private final Sender sender;
    private final String delay;
    private final Integer unicode;

    @Getter
    public static class MessageWithListBuilder {
        private String text;
        private PushType pushtype;
        private Sender sender;
        private String delay;
        private Integer unicode;

        public MessageWithListBuilder text(String text){
            this.text = text;
            return this;
        }

        public MessageWithListBuilder pushtype(PushType pushtype){
            this.pushtype = pushtype;
            return this;
        }

        public MessageWithListBuilder sender(Sender sender){
            this.sender = sender;
            return this;
        }

        public MessageWithListBuilder unicode(Integer unicode){
            this.unicode = unicode;
            return this;
        }

        public MessageWithListBuilder delay(Delay delay){
            if(delay != null){
                this.delay = delay.getValue();
            }
            return this;
        }

        public MessageWithList build(){
            return new MessageWithList(this.text, this.pushtype, this.sender, this.delay, this.unicode);
        }
    }

    public static MessageWithListBuilder builder(){
        return new MessageWithListBuilder();
    }
}
