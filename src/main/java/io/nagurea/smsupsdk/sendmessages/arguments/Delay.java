package io.nagurea.smsupsdk.sendmessages.arguments;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@ToString
public class Delay {
    private final String delay;

    public static class DelayBuilder {
        private static final DateTimeFormatter DELAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        public DelayBuilder delay(@NonNull LocalDateTime delay){
            this.delay = delay.format(DELAY_FORMATTER);
            return this;
        }

        public DelayBuilder delay(){
            delay(LocalDateTime.now());
            return this;
        }
    }
}
