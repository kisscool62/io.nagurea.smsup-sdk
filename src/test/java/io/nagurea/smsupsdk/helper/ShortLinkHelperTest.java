package io.nagurea.smsupsdk.helper;

import io.nagurea.smsupsdk.common.exception.TextWithoutShortPatternException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShortLinkHelperTest {

    private static final String TESTED_TEXT = "Message via API with a first link : <-short-> and a second one : <-short->";

    @Test
    void checkLinkListAndTextConsistent() {
        //given
        final List<String> links = Arrays.asList("this link", "that other link");

        //when
        ShortLinkHelper.checkLinkListAndTextConsistent(links, TESTED_TEXT);

        //then
        //everything OK
    }

    @Test
    void checkLinkListAndTextConsistentShouldRaiseDedicatedException() {
        //given
        final List<String> links = Collections.singletonList("this only link");

        //when then
        TextWithoutShortPatternException thrown = Assertions.assertThrows(TextWithoutShortPatternException.class, () -> {
            ShortLinkHelper.checkLinkListAndTextConsistent(links, TESTED_TEXT);
        }, "TextWithoutShortPatternException was expected");


    }
}