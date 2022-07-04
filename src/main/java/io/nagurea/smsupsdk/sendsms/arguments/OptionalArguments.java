package io.nagurea.smsupsdk.sendsms.arguments;

import io.nagurea.smsupsdk.sendsms.sender.NoSender;
import io.nagurea.smsupsdk.sendsms.sender.Sender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.nagurea.smsupsdk.common.http.SMSUpURLEncoder.encode;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Helper class for optional Arguments. These are not mandatory for UnitMessage action.
 */
@AllArgsConstructor
public abstract class OptionalArguments {
    private static final String PUSH_TYPE_NAME = "pushType";
    private static final String DELAY_NAME = "delay";
    private static final String SENDER_NAME = "sender";
    private static final String GSMSMSID_NAME = "gsmsmsid";

    @Getter private final PushType pushType;
    @Getter private final Delay delay;
    @Getter private Sender sender = NoSender.build();
    @Getter private final String gsmsmsid;
    @Getter private int unicode = 0;

    public boolean hasAtLeastOneArgument() {
        return delay != null || sender.whoSent().isPresent() || isNotEmpty(gsmsmsid);
    }

    public String toUrl(){
        String url = "";
        url += addArgument(PUSH_TYPE_NAME, this.pushType.getLabel());
        url += addArgument(DELAY_NAME, this.delay == null ? "" : this.delay.toString());
        url += addArgument(SENDER_NAME, this.sender.whoSent().orElse(null));
        url += addArgument(GSMSMSID_NAME, this.gsmsmsid);
        return url;
    }

    private String addArgument(final String parameterName, final String parameterValue) {
        if(isNotEmpty(parameterValue)){
            return "&" + parameterName + "=" + encode(parameterValue);
        }
        return "";
    }
}
