package io.nagurea.smsupsdk.common.e164;

import io.nagurea.smsupsdk.common.exception.E164FormatException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class E164HelperTest {

    @ParameterizedTest(name = "{index} => phoneNumberToTest={0}, shouldRaiseException={1}")
    @MethodSource
    void checkPhoneNumbersComplyE164Rules(final String phoneNumberToTest, boolean shouldRaiseException) {
        // given
        // According to method parameters phoneNumberToTest & shouldRaiseException

        // when
        boolean hasRaisedException = false;

        try {

            E164Helper.check(phoneNumberToTest);

        }catch (E164FormatException e){

            hasRaisedException = true;

        }
        // then
        assertEquals(shouldRaiseException, hasRaisedException);

    }

    // This method is automatically called by Parameterized test. Don't remove it
    private static Stream<Arguments> checkPhoneNumbersComplyE164Rules(){
        return Stream.of(
                arguments("41781234567", false),
                arguments("+41781234567", false),
                arguments("0781234567", true),
                arguments("41 78 123 45 67", true),
                arguments("33781234567", false)
        );
    }


}