package io.nagurea.smsupsdk.sendmessages.arguments;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@ToString
@Getter
public class Delay {
    private final String value;

    public static class DelayBuilder {
        private static final DateTimeFormatter DELAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        public DelayBuilder value(@NonNull LocalDateTime delay){
            this.value = delay.format(DELAY_FORMATTER);
            return this;
        }

        public DelayBuilder value(){
            value(LocalDateTime.now());
            return this;
        }
    }
}
