package io.nagurea.smsupsdk.sendsms.campaignwithlist.body;

import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;


public abstract class AbstractCampaignWithListDataBuilder <T extends MessageWithList.MessageWithListBuilder> {

    @Getter private final String text;
    @Getter private final Set<ListId> lists;
    @Getter private final @NonNull OptionalArguments optionalArguments;

    protected AbstractCampaignWithListDataBuilder(String text, Set<ListId> lists, @NonNull OptionalArguments optionalArguments) {
        this.text = text;
        this.lists = lists;
        this.optionalArguments = optionalArguments;
    }

    public String buildData() {
        return GsonHelper.toJson(buildCampaign()
        );
    }

    public Campaign buildCampaign() {
        return Campaign.builder()
                .sms(
                        SMS.builder()
                                .message(
                                        buildMessageBuilder()
                                                .build()
                                )
                                .lists(lists)
                                .build()
                )
                .build();
    }

    protected abstract T buildMessageBuilder();


}
