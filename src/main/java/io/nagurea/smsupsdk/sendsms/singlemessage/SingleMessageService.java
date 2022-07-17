package io.nagurea.smsupsdk.sendsms.singlemessage;


import io.nagurea.smsupsdk.common.e164.E164Helper;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException.RequiredParameterExceptionBuilder;
import io.nagurea.smsupsdk.common.http.get.GETSMSUpService;
import io.nagurea.smsupsdk.helper.json.GsonHelper;
import io.nagurea.smsupsdk.sendsms.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendsms.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendsms.sender.NoSender;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.util.UUID;

import static io.nagurea.smsupsdk.common.http.SMSUpURLEncoder.encode;


public class SingleMessageService extends GETSMSUpService {

    private static final String URL = "/send";
    private static final String TO = "to";
    private static final String SIMULATE_URL = "/simulate";
    private static final boolean SIMULATE = true;
    private static final boolean DO_NOT_SIMULATE = false;

    public SingleMessageService(String rootUrl) {
        super(rootUrl);
    }

    /**
     * Send a simple message for general purpose (called alert)
     * @param token SMSUp token
     * @param text Your message
     * @param to is the recipient to send the message to with E164 phone numbers
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
     * @param text Your message
     * @param to is the recipient to send the message to with E164 phone numbers
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
     * @param text Your message
     * @param to is the recipient to send the message to with E164 phone numbers
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
     * @param text Your message
     * @param to is the recipient to send the message to with E164 phone numbers
     * @param optionalArguments @{@link MarketingOptionalArguments} or @{@link AlertOptionalArguments}
     * @return UnitMessageResponse with detailed @UnitMessageResultResponse
     * @throws IOException when something got wrong during effective query to SMSUp
     */
    private SingleMessageResponse send(boolean simulate, @NonNull final String token, @NonNull final String text, @NonNull final String to, @NonNull OptionalArguments optionalArguments) throws IOException {
        E164Helper.check(to);
        final ImmutablePair<Integer, String> response = get(buildSendUrl(simulate, text, to, optionalArguments), token);
        final String body = response.getRight();
        final SingleMessageResultResponse responseObject = GsonHelper.fromJson(body, SingleMessageResultResponse.class);
        return SingleMessageResponse.builder()
                .uid(UUID.randomUUID().toString())
                .statusCode(response.getLeft())
                .effectiveResponse(responseObject)
                .build();
    }

    private String buildSendUrl(boolean simulate, @NonNull String text, @NonNull String to, @NonNull OptionalArguments optionalArguments) {
        final boolean textEmpty = StringUtils.isEmpty(text);
        final boolean toEmpty = StringUtils.isEmpty(to);
        final RequiredParameterExceptionBuilder exception = RequiredParameterException.builder();
        if(textEmpty){
            exception.requiredParam(TEXT, text);
        }
        if(toEmpty){
            exception.requiredParam(TO, to);
        }
        if(textEmpty || toEmpty) {
            throw exception.build();
        }
        String url = URL + (simulate ? SIMULATE_URL : "");
        final boolean hasAtLeastOneArgument = optionalArguments.hasAtLeastOneArgument();
        url = String.format("%s?%s=%s&%s=%s", url , TEXT, encode(text), TO, encode(to));
        if(hasAtLeastOneArgument){
            url += optionalArguments.toUrl();
        }
        return url;
    }

}
