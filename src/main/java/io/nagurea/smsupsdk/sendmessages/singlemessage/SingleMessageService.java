package io.nagurea.smsupsdk.sendmessages.singlemessage;


import com.google.gson.Gson;
import io.nagurea.smsupsdk.common.get.GETSMSUpService;
import io.nagurea.smsupsdk.sendmessages.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendmessages.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendmessages.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendmessages.sender.NoSender;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;


public class SingleMessageService extends GETSMSUpService {

    private static final String URL = "/send";
    private static final String TO = "to";
    private static final String SIMULATE_URL = "/simulate";
    private static final boolean SIMULATE = true;
    private static final boolean DO_NOT_SIMULATE = false;

    protected SingleMessageService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Send a simple message for general purpose (called alert)
     * @param token SMSUp token
     * @param text to send
     * @param to is the recipient to send the message to
     * @return UnitMessageResponse with detailed @UnitMessageResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public SingleMessageResponse sendAlert(String token, String text, String to) throws IOException {
        return send(DO_NOT_SIMULATE, token, text, to, AlertOptionalArguments.builder()
                .sender( NoSender.build())
                .build());
    }

    public SingleMessageResponse simulateSendAlert(String token, String text, String to) throws IOException {
        return send(SIMULATE, token, text, to, AlertOptionalArguments.builder()
                .sender( NoSender.build())
                .build());
    }

    /**
     * Send a simple message for general purpose (called alert)
     * @param token SMSUp token
     * @param text to send
     * @param to is the recipient to send the message to
     * @param alertOptionalArguments is a wrapper of arguments
     * @return UnitMessageResponse with detailed @UnitMessageResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public SingleMessageResponse sendAlert(String token, String text, String to, @NonNull AlertOptionalArguments alertOptionalArguments) throws IOException {
        return send(DO_NOT_SIMULATE, token, text, to, alertOptionalArguments);
    }

    public SingleMessageResponse simulateSendAlert(String token, String text, String to, @NonNull AlertOptionalArguments alertOptionalArguments) throws IOException {
        return send(SIMULATE, token, text, to, alertOptionalArguments);
    }

    /**
     * Send a message for commercial purpose (called marketing) with STOP function
     * @param token SMSUp token
     * @param text to send
     * @param to is the recipient to send the message to
     * @return UnitMessageResponse with detailed @UnitMessageResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    public SingleMessageResponse sendMarketing(String token, String text, String to) throws IOException {
        return send(DO_NOT_SIMULATE, token, text, to, MarketingOptionalArguments.builder().sender(NoSender.build()).build());
    }

    public SingleMessageResponse simulateSendMarketing(String token, String text, String to) throws IOException {
        return send(SIMULATE, token, text, to, MarketingOptionalArguments.builder().sender(NoSender.build()).build());
    }

    public SingleMessageResponse sendMarketing(String token, String text, String to, @NonNull MarketingOptionalArguments marketingOptionalArguments) throws IOException {
        return send(DO_NOT_SIMULATE, token, text, to, marketingOptionalArguments);
    }

    public SingleMessageResponse simulateSendMarketing(String token, String text, String to, @NonNull MarketingOptionalArguments marketingOptionalArguments) throws IOException {
        return send(SIMULATE, token, text, to, marketingOptionalArguments);
    }

    /**
     *
     * @param token SMSUp token
     * @param text to send
     * @param to is the recipient to send the message to
     * @param optionalArguments @{@link MarketingOptionalArguments} or @{@link AlertOptionalArguments}
     * @return UnitMessageResponse with detailed @UnitMessageResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    private SingleMessageResponse send(boolean simulate, @NonNull final String token, @NonNull final String text, @NonNull final String to, @NonNull OptionalArguments optionalArguments) throws IOException {
        final ImmutablePair<Integer, String> response = get(buildSendUrl(simulate, text, to, optionalArguments), token);
        final String body = response.getRight();
        final SingleMessageResultResponse responseObject = new Gson().fromJson(body, SingleMessageResultResponse.class);
        return SingleMessageResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildSendUrl(boolean simulate, @NonNull String text, @NonNull String to, @NonNull OptionalArguments optionalArguments) {
        final String rootUrl = URL + (simulate ? SIMULATE_URL : "");
        String url = rootUrl;
        final boolean textNotEmpty = StringUtils.isNotEmpty(text);
        final boolean toNotEmpty = StringUtils.isNotEmpty(to);
        final boolean hasAtLeastOneArgument = optionalArguments.hasAtLeastOneArgument();
        if(textNotEmpty && toNotEmpty && hasAtLeastOneArgument){
            url = String.format("%s?%s=%s&%s=%s%s", rootUrl , TEXT, text, TO, to, optionalArguments.toUrl());
        }
        return url;
    }

}
