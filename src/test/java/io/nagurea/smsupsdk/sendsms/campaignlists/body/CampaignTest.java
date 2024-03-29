package io.nagurea.smsupsdk.sendsms.campaignlists.body;

import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.Delay;
import io.nagurea.smsupsdk.sendsms.campaign.body.Campaign;
import io.nagurea.smsupsdk.sendsms.common.Gsm;
import io.nagurea.smsupsdk.sendsms.campaign.body.Message;
import io.nagurea.smsupsdk.sendsms.campaign.body.SMS;
import io.nagurea.smsupsdk.sendsms.common.Recipients;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static io.nagurea.smsupsdk.sendsms.arguments.PushType.ALERT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CampaignTest {

    @Test
    void builder() {
        //given
        final Set<Gsm> gsm = new HashSet<>();
        gsm.add(Gsm.builder().gsmsmsid("100").value("41781234567").build());
        gsm.add(Gsm.builder().gsmsmsid("101").value("41781234566").build());

        //when
        final Campaign campaign = Campaign.builder()
                .sms(
                        SMS.builder()
                                .message(
                                        Message.builder()
                                                .text("Message Via API")
                                                .pushtype(ALERT)
                                                .sender(Sender.build("Illidan"))
                                                .delay(
                                                        Delay.builder()
                                                                .value(LocalDateTime.of(2022, 4, 18, 10, 56, 14))
                                                        .build())
                                                .unicode(0)
                                                .build()
                                )
                                .recipients(
                                        Recipients.builder()
                                                .gsm(gsm)
                                                .build()
                                )
                                .build()
                )
                .build();

        //then
        final String expected = "{\n" +
                "  \"sms\": {\n" +
                "    \"message\": {\n" +
                "      \"text\": \"Message Via API\",\n" +
                "      \"pushtype\": \"alert\",\n" +
                "      \"sender\": \"Illidan\",\n" +
                "      \"delay\": \"2022-04-18 10:56:14\",\n" +
                "      \"unicode\": 0\n" +
                "    },\n" +
                "    \"recipients\": {\n" +
                "      \"gsm\": [\n" +
                "        {\n" +
                "          \"gsmsmsid\": \"100\",\n" +
                "          \"value\": \"41781234567\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"gsmsmsid\": \"101\",\n" +
                "          \"value\": \"41781234566\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        assertEquals(
                expected.replaceAll("\\s+","")
                , GsonHelper.toJson(campaign).replaceAll("\\s+","")
        );
    }
}