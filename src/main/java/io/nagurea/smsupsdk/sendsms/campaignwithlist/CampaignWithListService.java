package io.nagurea.smsupsdk.sendsms.campaignwithlist;

import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.CampaignWithListDataBuilder;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.ListId;
import io.nagurea.smsupsdk.sendsms.sender.NoSender;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class CampaignWithListService extends POSTSMSUpService {
    private static final String URL = "/send/lists";
    private static final String SIMULATE_URL = "/simulate";

    public CampaignWithListService(String rootUrl) {
        super(rootUrl);
    }


    /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration @{@link ListId}
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendAlert(String token, String text, Set<ListId> lists) throws IOException {
        return send(false, token, text, lists, AlertOptionalArguments.builder().sender(NoSender.build()).build());
    }

    public CampaignWithListResponse simulateSendAlert(String token, String text, Set<ListId> lists) throws IOException {
        return send(true, token, text, lists, AlertOptionalArguments.builder().sender(NoSender.build()).build());
    }

     /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration @{@link ListId}
     * @param alertOptionalArgument is argument wrapper object
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendAlert(String token, String text, Set<ListId> lists, @NonNull AlertOptionalArguments alertOptionalArgument) throws IOException {
        return send(false, token, text, lists, alertOptionalArgument);
    }

    /**
     * Simulate a send of a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration @{@link ListId}
     * @param alertOptionalArgument is argument wrapper object
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse simulateSendAlert(String token, String text, Set<ListId> lists, @NonNull AlertOptionalArguments alertOptionalArgument) throws IOException {
        return send(true, token, text, lists, alertOptionalArgument);
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendMarketing(String token, String text, Set<ListId> lists) throws IOException {
        return send(false, token, text, lists, MarketingOptionalArguments.builder().sender(NoSender.build()).build());
    }

    /**
     * Simulate a send to a marketing campaign
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse simulateSendMarketing(String token, String text, Set<ListId> lists )throws IOException {
        return send(true, token, text, lists, MarketingOptionalArguments.builder().sender(NoSender.build()).build());
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendMarketing(String token, String text, Set<ListId> lists, @NonNull MarketingOptionalArguments marketingOptionalArguments) throws IOException {
        return send(false, token, text, lists, marketingOptionalArguments);
    }

    /**
     * Simulate a send to a marketing technique
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse simulateSendMarketing(String token, String text, Set<ListId> lists, @NonNull MarketingOptionalArguments marketingOptionalArguments) throws IOException {
        return send(true, token, text, lists, marketingOptionalArguments);
    }


    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param optionalArguments @{@link MarketingOptionalArguments} or @{@link AlertOptionalArguments}
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    private CampaignWithListResponse send(boolean simulate, String token, String text, Set<ListId> lists, @NonNull OptionalArguments optionalArguments) throws IOException {
        final ImmutablePair<Integer, String> response =
                post(
                        buildUrl(simulate),
                        token,
                        CampaignWithListDataBuilder.builder().text(text).lists(lists).optionalArguments(optionalArguments).build().buildData());

        final CampaignWithListResultResponse responseObject = GsonHelper.fromJson(response.getRight(), CampaignWithListResultResponse.class);
        return CampaignWithListResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrl(boolean simulate) {
        return URL + (simulate ? SIMULATE_URL : "");
    }


}
