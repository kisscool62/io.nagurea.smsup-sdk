package io.nagurea.smsupsdk.sendsms.shorturl.body;

import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaign.body.AbstractCampaignDataBuilder;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

public class CampaignWithLinksDataBuilder extends AbstractCampaignDataBuilder<MessageWithLinks.MessageWithLinksBuilder> {

    @Getter private final List<String> links;

    public CampaignWithLinksDataBuilder(String text, @NonNull OptionalArguments optionalArguments, List<String> links) {
        super(text, optionalArguments);
        this.links = links;
    }

    protected MessageWithLinks.MessageWithLinksBuilder buildMessageBuilder(){
        final MessageWithLinks.MessageWithLinksBuilder messageWithLinksBuilder = initMessage(getText(), getOptionalArguments());
        messageWithLinksBuilder.links(this.links);

        return messageWithLinksBuilder;
    }

    private MessageWithLinks.MessageWithLinksBuilder initMessage(String text, OptionalArguments optionalArguments) {
        return MessageWithLinks.builder()
                .unicode(optionalArguments.getUnicode())
                .delay(optionalArguments.getDelay())
                .text(text)
                .pushtype(optionalArguments.getPushType())
                .sender(optionalArguments.getSender());
    }

}
