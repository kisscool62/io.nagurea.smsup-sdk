package io.nagurea.smsupsdk.sendsms.campaign.body;

import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    private final String text;
    private final PushType pushtype;
    private final Sender sender;
    private final String delay;
    private final String gsmsmsid;
    private final Integer unicode;

    @Getter
    public static class MessageBuilder {
        private String text;
        private PushType pushtype;
        private Sender sender;
        private String delay;
        private String gsmsmsid;
        private Integer unicode;

        public MessageBuilder unicode(Integer unicode){
            this.unicode = unicode;
            return this;
        }

        public MessageBuilder gsmsmsid(String gsmsmsid){
            this.gsmsmsid = gsmsmsid;
            return this;
        }

        public MessageBuilder sender(Sender sender){
            this.sender = sender;
            return this;
        }

        public MessageBuilder pushtype(PushType pushtype){
            this.pushtype = pushtype;
            return this;
        }

        public MessageBuilder text(String text){
            this.text = text;
            return this;
        }

        public MessageBuilder delay(Delay delay){
            if(delay != null){
                this.delay = delay.getValue();
            }
            return this;
        }

        public Message build(){
            return new Message(this.text, this.pushtype, this.sender, this.delay, this.gsmsmsid, this.unicode);
        }
    }

    public static Message.MessageBuilder builder(){
        return new Message.MessageBuilder();
    }
}
