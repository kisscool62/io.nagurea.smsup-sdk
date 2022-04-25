package io.nagurea.smsupsdk.sendmessages.campaign.body;

import io.nagurea.smsupsdk.sendmessages.arguments.PushType;
import io.nagurea.smsupsdk.sendmessages.sender.Sender;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class Message {
    private final String text;
    private final PushType pushtype;
    private final Sender sender;
    private final String delay;
    private final Integer unicode;

    public static class MessageBuilder {
        private static final DateTimeFormatter DELAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    }
}
