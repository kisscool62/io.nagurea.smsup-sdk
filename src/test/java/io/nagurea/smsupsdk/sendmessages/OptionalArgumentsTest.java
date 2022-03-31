package io.nagurea.smsupsdk.sendmessages;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionalArgumentsTest {

    @Test
    void builder() {
        //given
        final OptionalArguments.OptionalArgumentsBuilder builder = OptionalArguments.builder();
        final LocalDateTime now = LocalDateTime.of(2022, Month.MARCH, 10, 1, 58);

        //when
        final OptionalArguments optionalArguments = builder.pushType(PushType.ALERT).delay(now).build();

        //then
        assertEquals("2022-03-10 01:58:00", optionalArguments.getDelay());
        assertEquals(PushType.ALERT, optionalArguments.getPushType());
    }
}