package io.nagurea.smsupsdk.sendsms.shorturl.body;

import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.arguments.PushType;
import io.nagurea.smsupsdk.sendsms.campaign.body.Message;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.MessageWithList;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;


public class MessageWithLinks extends Message {

    @Getter @Singular private final List<String> links = new ArrayList<>();

    @Builder
    private MessageWithLinks(String text, PushType pushtype, Sender sender, String delay, String gsmsmsid, Integer unicode, @NonNull List<String> links) {
        super(text, pushtype, sender, delay, gsmsmsid, unicode);
        this.links.addAll(links);
    }

    public static class MessageWithLinksBuilder extends Message.MessageBuilder {
        private String delay;

        public MessageWithLinksBuilder delay(Delay delay){
            if(delay != null){
                this.delay = delay.getValue();
            }
            return this;
        }
    }


}
