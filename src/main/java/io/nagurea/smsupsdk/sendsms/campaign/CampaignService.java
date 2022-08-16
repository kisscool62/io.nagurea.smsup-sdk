package io.nagurea.smsupsdk.sendsms.campaign;

import io.nagurea.smsupsdk.common.http.post.POSTSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.campaign.body.CampaignDataBuilder;
import io.nagurea.smsupsdk.sendsms.campaign.body.Recipients;
import io.nagurea.smsupsdk.sendsms.sender.NoSender;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

public class CampaignService extends POSTSMSUpService {
    private static final String URL = "/send";

    public CampaignService(String rootUrl) {
        super(rootUrl);
    }


    /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param recipients is actually the set of couples (gsmId, phone number E164) represented by @{@link Recipients}
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendAlert(String token, String text, Recipients recipients) throws IOException {
        return send(token, text, recipients, AlertOptionalArguments.builder().sender(NoSender.build()).build());
    }

     /**
     * Send a campaign message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param recipients is actually the set of couples (gsmId, phone number E164) represented by @{@link Recipients}
     * @param alertOptionalArgument is argument wrapper object
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendAlert(String token, String text, Recipients recipients, @NonNull AlertOptionalArguments alertOptionalArgument) throws IOException {
        return send(token, text, recipients, alertOptionalArgument);
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param recipients to send the text to with E164 phone numbers
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendMarketing(String token, String text, Recipients recipients) throws IOException {
        return send(token, text, recipients, MarketingOptionalArguments.builder().sender(NoSender.build()).build());
    }

    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param recipients to send the text to with E164 phone numbers
     * @param marketingOptionalArguments who do you want the sms appears to be sent by
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public CampaignResponse sendMarketing(String token, String text, Recipients recipients, @NonNull MarketingOptionalArguments marketingOptionalArguments) throws IOException {
        return send( token, text, recipients, marketingOptionalArguments);
    }


    /**
     *
     * @param token SMSUp token
     * @param text Your message
     * @param recipients to send the text to with E164 phone numbers
     * @param optionalArguments @{@link MarketingOptionalArguments} or @{@link AlertOptionalArguments}
     * @return CampaignResponse with detailed @{@link CampaignResultResponse}
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    private CampaignResponse send(String token, String text, Recipients recipients, @NonNull OptionalArguments optionalArguments) throws IOException {
        recipients.check();
        final ImmutablePair<Integer, String> response =
                post(URL, token,
                CampaignDataBuilder.builder()
                        .text(text)
                        .optionalArguments(optionalArguments)
                        .build().buildData()
        );
        final CampaignResultResponse responseObject = GsonHelper.fromJson(response.getRight(), CampaignResultResponse.class);
        return CampaignResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

}
