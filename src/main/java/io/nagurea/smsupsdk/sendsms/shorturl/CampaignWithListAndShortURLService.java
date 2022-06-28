package io.nagurea.smsupsdk.sendsms.shorturl;

import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.ShortLinkHelper;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.CampaignWithListResponse;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.CampaignWithListResultResponse;
import io.nagurea.smsupsdk.sendsms.campaignwithlist.body.ListId;
import io.nagurea.smsupsdk.sendsms.sender.NoSender;
import io.nagurea.smsupsdk.sendsms.shorturl.body.CampaignWithListAndLinksDataBuilder;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CampaignWithListAndShortURLService extends POSTSMSUpService {
    private static final String URL = "/send/lists";
    private static final String SIMULATE_URL = "/simulate";

    public CampaignWithListAndShortURLService(String rootUrl) {
        super(rootUrl);
    }


    /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration @{@link ListId}
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendAlert(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull List<String> links) throws IOException {
        return send(false, token, text, lists, AlertOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

    public CampaignWithListResponse simulateSendAlert(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull List<String> links) throws IOException {
        return send(true, token, text, lists, AlertOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

     /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration @{@link ListId}
     * @param alertOptionalArgument is argument wrapper object
     * @param links The URLs to shorten
      * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendAlert(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull AlertOptionalArguments alertOptionalArgument, @NonNull List<String> links) throws IOException {
        return send(false, token, text, lists, alertOptionalArgument, links);
    }

    /**
     * Simulate a send of a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration @{@link ListId}
     * @param alertOptionalArgument is argument wrapper object
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse simulateSendAlert(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull AlertOptionalArguments alertOptionalArgument, @NonNull List<String> links) throws IOException {
        return send(true, token, text, lists, alertOptionalArgument, links);
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendMarketing(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull List<String> links) throws IOException {
        return send(false, token, text, lists, MarketingOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

    /**
     * Simulate a send to a marketing campaign
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse simulateSendMarketing(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull List<String> links)throws IOException {
        return send(true, token, text, lists, MarketingOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse sendMarketing(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull MarketingOptionalArguments marketingOptionalArguments, @NonNull List<String> links) throws IOException {
        return send(false, token, text, lists, marketingOptionalArguments, links);
    }

    /**
     * Simulate a send to a marketing technique
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignWithListResponse simulateSendMarketing(@NonNull String token, @NonNull String text, @NonNull Set<ListId> lists, @NonNull MarketingOptionalArguments marketingOptionalArguments, @NonNull List<String> links) throws IOException {
        return send(true, token, text, lists, marketingOptionalArguments, links);
    }


    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param lists to send the id of lists existing in smsUp configuration
     * @param optionalArguments @{@link MarketingOptionalArguments} or @{@link AlertOptionalArguments}
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignWithListResultResponse}
     * @throws IOException
     */
    private CampaignWithListResponse send(
            boolean simulate,
            @NonNull String token,
            @NonNull String text,
            @NonNull Set<ListId> lists,
            @NonNull OptionalArguments optionalArguments,
            @NonNull List<String> links) throws IOException {

        ShortLinkHelper.checkLinkListAndTextConsistent(links, text);

        final String data = new CampaignWithListAndLinksDataBuilder(text, lists, optionalArguments, links).buildData();

        final ImmutablePair<Integer, String> response = post(buildUrl(simulate), token, data);
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
