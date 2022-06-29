package io.nagurea.smsupsdk.sendsms.shorturl.body;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.nagurea.smsupsdk.sendsms.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.Campaign;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.ListId;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CampaignWithListAndLinksDataBuilderTest {

    @Test
    void testBuild(){
        // given
        final String text = "Your <-short-> message <-short->";
        final Set<ListId> listIds = Sets.newHashSet(ListId.builder().value(42).build());
        final OptionalArguments optionalArguments = MarketingOptionalArguments.builder().unicode(2).build();
        final List<String> links = Lists.newArrayList("link1", "link2");


        // when
        final CampaignWithListAndLinksDataBuilder campaignBuilder = new CampaignWithListAndLinksDataBuilder(text, listIds, optionalArguments, links);
        final Campaign campaign = campaignBuilder.buildCampaign();

        // then
        assertEquals(links.size(), ((MessageWithListAndLinks)(campaign.getSms().getMessage())).getLinks().size());
        assertEquals(links, ((MessageWithListAndLinks)(campaign.getSms().getMessage())).getLinks());

    }

}