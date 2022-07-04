package io.nagurea.smsupsdk.lists.npai;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.lists.get.list.Contact;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class NPAIContact extends Contact {

    @SerializedName(value = "campaign_id")
    final Integer campaignId;

}
