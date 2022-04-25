package io.nagurea.smsupsdk.sendmessages;

import io.nagurea.smsupsdk.sendmessages.arguments.Delay;
import io.nagurea.smsupsdk.sendmessages.arguments.MarketingOptionalArguments;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static io.nagurea.smsupsdk.sendmessages.arguments.PushType.MARKETING;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionalArgumentsTest {

    @Test
    void builder() {
        //given
        final MarketingOptionalArguments.MarketingOptionalArgumentsBuilder builder = MarketingOptionalArguments.builder();
        final LocalDateTime now = LocalDateTime.of(2022, Month.MARCH, 10, 1, 58);

        //when
        final MarketingOptionalArguments optionalArguments =
                builder.delay(
                    Delay.builder().value(now).build())
                .build();

        //then
        assertEquals("2022-03-10 01:58:00", optionalArguments.getDelay().toString());
        assertEquals(MARKETING, optionalArguments.getPushType());
    }
}