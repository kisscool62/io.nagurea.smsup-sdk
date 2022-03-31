package io.nagurea.smsupsdk.sendmessages.unitmessage;


import com.google.gson.Gson;
import io.nagurea.smsupsdk.common.get.GETSMSUpService;
import io.nagurea.smsupsdk.sendmessages.arguments.AlertOptionalArguments;
import io.nagurea.smsupsdk.sendmessages.arguments.MarketingOptionalArguments;
import io.nagurea.smsupsdk.sendmessages.arguments.OptionalArguments;
import io.nagurea.smsupsdk.sendmessages.sender.NoSender;
import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.UUID;


public class UnitMessageService extends GETSMSUpService {

    private static final String URL = "/SEND";
    private static final String TEXT = "text";
    private static final String TO = "to";

    protected UnitMessageService(String rootUrl) {
        super(rootUrl);
    }

    public UnitMessageResponse sendAlert(String token, String text, String to) throws IOException {
        return sendAlert(token, text, to, NoSender.build());
    }

    public UnitMessageResponse sendMarketing(String token, String text, String to) throws IOException {
        return sendMarketing(token, text, to, NoSender.build());
    }

    public UnitMessageResponse sendAlert(String token, String text, String to, Sender sender) throws IOException {
        final AlertOptionalArguments alertOptionalArgument = AlertOptionalArguments.builder()
                .sender(sender)
                .build();
        return send(token, text, to, alertOptionalArgument);
    }

    public UnitMessageResponse sendMarketing(String token, String text, String to, Sender sender) throws IOException {
        final MarketingOptionalArguments marketingOptionalArguments = MarketingOptionalArguments.builder()
                .sender(sender)
                .build();
        return send(token, text, to, marketingOptionalArguments);
    }

    private UnitMessageResponse send(@NonNull final String token, @NonNull final String text, @NonNull final String to, OptionalArguments optionalArguments) throws IOException {

        final String response = get(buildSendUrl(text, to, optionalArguments), token);

        //TODO catch result from query
        final UnitMessageResultResponse responseObject = new Gson().fromJson(response, UnitMessageResultResponse.class);

        return UnitMessageResponse.builder()
                .uid(UUID.randomUUID().toString())
                .effectiveResponse(responseObject)
                .build();

    }

    private String buildSendUrl(String text, String to, OptionalArguments optionalArguments) {
        String url = URL;
        final boolean textNotEmpty = StringUtils.isNotEmpty(text);
        final boolean toNotEmpty = StringUtils.isNotEmpty(to);
        final boolean hasAtLeastOneArgument = optionalArguments.hasAtLeastOneArgument();
        if(textNotEmpty && toNotEmpty && hasAtLeastOneArgument){
            url +="?";
            url += (TEXT + "=" + text);
            url += "&";
            url += (TO + "=" + to);
            url += optionalArguments.toUrl();
        }
        return url;
    }

}
