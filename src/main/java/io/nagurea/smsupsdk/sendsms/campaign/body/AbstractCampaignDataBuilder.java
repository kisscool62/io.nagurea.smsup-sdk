package io.nagurea.smsupsdk.sendsms.campaign.body;

import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import lombok.Getter;
import lombok.NonNull;


public abstract class AbstractCampaignDataBuilder<T extends Message.MessageBuilder> {

    @Getter private final String text;
    @Getter private final @NonNull OptionalArguments optionalArguments;

    protected AbstractCampaignDataBuilder(String text, @NonNull OptionalArguments optionalArguments) {
        this.text = text;
        this.optionalArguments = optionalArguments;
    }

    public String buildData() {
        return GsonHelper.toJson(buildCampaign()
        );
    }

    public io.nagurea.smsupsdk.sendsms.campaign.body.Campaign buildCampaign() {
        return Campaign.builder()
                .sms(
                        SMS.builder()
                                .message(
                                        buildMessageBuilder()
                                                .build()
                                )
                                .build()
                )
                .build();
    }

    protected abstract T buildMessageBuilder();


}
