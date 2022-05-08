package io.nagurea.smsupsdk.common.e164;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class E164HelperTest {

    @Test
    void checkIsOk() {
        // given
        final String phoneNumberToTest = "41781234567";

        // when
        E164Helper.check(phoneNumberToTest);
        final boolean hasSucceeded = true;

        // then
        assertTrue(hasSucceeded);
    }
}