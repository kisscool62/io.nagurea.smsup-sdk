package io.nagurea.smsupsdk.sendsms.campaignwithlist.body;

import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import lombok.Builder;
import lombok.NonNull;

import java.util.Set;

public class CampaignWithListDataBuilder extends AbstractCampaignWithListDataBuilder<MessageWithList.MessageWithListBuilder> {

    @Builder
    protected CampaignWithListDataBuilder(String text, Set<ListId> lists, @NonNull OptionalArguments optionalArguments) {
        super(text, lists, optionalArguments);
    }

    protected MessageWithList.MessageWithListBuilder buildMessageBuilder(){
        return initMessage(getText(), getOptionalArguments());
    }

    private MessageWithList.MessageWithListBuilder initMessage(String text, OptionalArguments optionalArguments) {
        return MessageWithList.builder()
                .unicode(optionalArguments.getUnicode())
                .delay(optionalArguments.getDelay())
                .text(text)
                .pushtype(optionalArguments.getPushType())
                .sender(optionalArguments.getSender());
    }
}
