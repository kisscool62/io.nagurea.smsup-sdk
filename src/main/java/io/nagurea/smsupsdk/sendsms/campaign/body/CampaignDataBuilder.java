package io.nagurea.smsupsdk.sendsms.campaign.body;

import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.common.Recipients;
import lombok.Builder;
import lombok.NonNull;

public class CampaignDataBuilder extends AbstractCampaignDataBuilder<Message.MessageBuilder> {

    @Builder
    protected CampaignDataBuilder(String text, @NonNull Recipients recipients, @NonNull OptionalArguments optionalArguments) {
        super(text, recipients, optionalArguments);
    }

    protected Message.MessageBuilder buildMessageBuilder(){
        return initMessage(getText(), getOptionalArguments());
    }

    private Message.MessageBuilder initMessage(String text, OptionalArguments optionalArguments) {
        return   Message.builder()
                .unicode(optionalArguments.getUnicode())
                .delay(optionalArguments.getDelay())
                .text(text)
                .gsmsmsid(optionalArguments.getGsmsmsid())
                .pushtype(optionalArguments.getPushType())
                .sender(optionalArguments.getSender());
    }
}
