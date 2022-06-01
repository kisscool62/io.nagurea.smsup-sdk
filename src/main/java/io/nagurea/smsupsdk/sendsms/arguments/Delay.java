package io.nagurea.smsupsdk.sendsms.arguments;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
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

    @Override
    public String toString() {
        return value.toString();
    }
}
