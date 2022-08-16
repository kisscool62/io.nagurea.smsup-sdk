package io.nagurea.smsupsdk.sendsms.shorturl;

import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.ShortLinkHelper;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaign.CampaignResponse;
import io.nagurea.smsupsdk.sendsms.campaign.CampaignResultResponse;
import io.nagurea.smsupsdk.sendsms.sender.NoSender;
import io.nagurea.smsupsdk.sendsms.shorturl.body.CampaignWithLinksDataBuilder;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CampaignWithShortURLService extends POSTSMSUpService {
    private static final String URL = "/send";
    private static final String SIMULATE_URL = "/simulate";

    public CampaignWithShortURLService(String rootUrl) {
        super(rootUrl);
    }


    /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendAlert(@NonNull String token, @NonNull String text, @NonNull List<String> links) throws IOException {
        return send(false, token, text, AlertOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

    public CampaignResponse simulateSendAlert(@NonNull String token, @NonNull String text, @NonNull List<String> links) throws IOException {
        return send(true, token, text, AlertOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

     /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param alertOptionalArgument is argument wrapper object
     * @param links The URLs to shorten
      * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendAlert(@NonNull String token, @NonNull String text, @NonNull AlertOptionalArguments alertOptionalArgument, @NonNull List<String> links) throws IOException {
        return send(false, token, text, alertOptionalArgument, links);
    }

    /**
     * Simulate a send of a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param alertOptionalArgument is argument wrapper object
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse simulateSendAlert(@NonNull String token, @NonNull String text, @NonNull AlertOptionalArguments alertOptionalArgument, @NonNull List<String> links) throws IOException {
        return send(true, token, text, alertOptionalArgument, links);
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendMarketing(@NonNull String token, @NonNull String text, @NonNull List<String> links) throws IOException {
        return send(false, token, text, MarketingOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

    /**
     * Simulate a send to a marketing campaign
     * @param token SMSUp token
     * @param text Your message
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse simulateSendMarketing(@NonNull String token, @NonNull String text, @NonNull List<String> links)throws IOException {
        return send(true, token, text, MarketingOptionalArguments.builder().sender(NoSender.build()).build(), links);
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendMarketing(@NonNull String token, @NonNull String text, @NonNull MarketingOptionalArguments marketingOptionalArguments, @NonNull List<String> links) throws IOException {
        return send(false, token, text, marketingOptionalArguments, links);
    }

    /**
     * Simulate a send to a marketing technique
     * @param token SMSUp token
     * @param text Your message
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse simulateSendMarketing(@NonNull String token, @NonNull String text, @NonNull MarketingOptionalArguments marketingOptionalArguments, @NonNull List<String> links) throws IOException {
        return send(true, token, text, marketingOptionalArguments, links);
    }


    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param optionalArguments @{@link MarketingOptionalArguments} or @{@link AlertOptionalArguments}
     * @param links The URLs to shorten
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    private CampaignResponse send(
            boolean simulate,
            @NonNull String token,
            @NonNull String text,
            @NonNull OptionalArguments optionalArguments,
            @NonNull List<String> links) throws IOException {

        ShortLinkHelper.checkLinkListAndTextConsistent(links, text);

        final String data = new CampaignWithLinksDataBuilder(text, optionalArguments, links).buildData();

        final ImmutablePair<Integer, String> response = post(buildUrl(simulate), token, data);
        final CampaignResultResponse responseObject = GsonHelper.fromJson(response.getRight(), CampaignResultResponse.class);
        return CampaignResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildUrl(boolean simulate) {
        return URL + (simulate ? SIMULATE_URL : "");
    }


}
