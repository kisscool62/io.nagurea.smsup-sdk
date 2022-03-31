package io.nagurea.smsupsdk.sendmessages.arguments;

import io.nagurea.smsupsdk.sendmessages.PushType;
import io.nagurea.smsupsdk.sendmessages.sender.NoSender;
import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Helper class for optional Arguments. These are not mandatory for UnitMessage action.
 */
@Builder
public class OptionalArguments {
    private static final String PUSH_TYPE_NAME = "pushType";
    private static final String DELAY_NAME = "delay";
    private static final String SENDER_NAME = "sender";
    private static final String GSMSMSID_NAME = "gsmsmsid";

    @Getter private final PushType pushType;
    @Getter private final String delay;
    @Getter private final Sender sender = NoSender.build();
    @Getter private final String gsmsmsid;

    public boolean hasAtLeastOneArgument() {
        return isNotEmpty(delay) || sender.whoSent().isPresent() || isNotEmpty(gsmsmsid);
    }

    public static class OptionalArgumentsBuilder {
        private static final DateTimeFormatter DELAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        public OptionalArgumentsBuilder delay(@NonNull LocalDateTime delay){
            this.delay = delay.format(DELAY_FORMATTER);
            return this;
        }
    }

    public String toUrl(){
        String url = "";
        url += addArgument(PUSH_TYPE_NAME, this.pushType.getLabel());
        url += addArgument(DELAY_NAME, this.delay);
        url += addArgument(SENDER_NAME, this.sender.whoSent().orElse(null));
        url += addArgument(GSMSMSID_NAME, this.gsmsmsid);
        return url;
    }

    private String addArgument(final String parameterName, final String parameterValue) {
        if(isNotEmpty(parameterValue)){
            return "&" + parameterName + "=" + parameterValue;
        }
        return "";
    }
}
