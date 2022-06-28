package io.nagurea.smsupsdk.sendsms.shorturl.body;

import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.AbstractCampaignWithListDataBuilder;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.ListId;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
import java.util.Set;

public class CampaignWithListAndLinksDataBuilder extends AbstractCampaignWithListDataBuilder<MessageWithListWithLinks.MessageWithListWithLinksBuilder> {

    @Getter private final List<String> links;

    public CampaignWithListAndLinksDataBuilder(String text, Set<ListId> lists, @NonNull OptionalArguments optionalArguments, List<String> links) {
        super(text, lists, optionalArguments);
        this.links = links;
    }

    protected MessageWithListWithLinks.MessageWithListWithLinksBuilder buildMessageBuilder(){
        final MessageWithListWithLinks.MessageWithListWithLinksBuilder messageWithLinksBuilder = initMessage(getText(), getOptionalArguments());
        messageWithLinksBuilder.links(this.links);

        return messageWithLinksBuilder;
    }

    private MessageWithListWithLinks.MessageWithListWithLinksBuilder initMessage(String text, OptionalArguments optionalArguments) {
        return MessageWithListWithLinks.builder()
                .unicode(optionalArguments.getUnicode())
                .delay(optionalArguments.getDelay())
                .text(text)
                .pushtype(optionalArguments.getPushType())
                .sender(optionalArguments.getSender());
    }

}
