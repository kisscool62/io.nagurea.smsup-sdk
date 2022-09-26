package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetentionWithExpiration;
import org.junit.jupiter.api.Test;

import static io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit.MONTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListRetentionTest {

    @Test
    void buildListRetentionTime() {
        //given
        final ListRetention retentionTime = ListRetention.buildListRetention(
                ListRetentionWithExpiration.builder()
                        .number(2)
                        .timeUnit(MONTH)
                        .build()
        );

        //when
        final String serializedRetentionTime = retentionTime.toString();

        //then
        assertEquals("2m", serializedRetentionTime);

    }

    @Test
    void buildEndlessRetentionTime() {
        //given
        final ListRetention retentionTime = ListRetention.buildEndlessRetention();

        //when
        final String serializedRetentionTime = retentionTime.toString();

        //then
        assertEquals("-1", serializedRetentionTime);

    }

    @Test
    void endlessRetentionShouldBeValid() {
        //given
        final ListRetention retentionTime = ListRetention.buildEndlessRetention();

        //when
        final boolean validationResult = retentionTime.isValid();

        //then
        assertTrue(validationResult);

    }

    @Test
    void listRetentionShouldBeValidWhenInf26M() {
        //given
        final ListRetention retentionTime = ListRetention.buildListRetention(
                ListRetentionWithExpiration.builder()
                        .number(26)
                        .timeUnit(MONTH)
                        .build()
        );

        //when
        final boolean validationResult = retentionTime.isValid();

        //then
        assertTrue(validationResult);

    }

    @Test
    void listRetentionShouldNotBeValidWhenSup26M() {
        //given
        final ListRetention retentionTime = ListRetention.buildListRetention(
                ListRetentionWithExpiration.builder()
                        .number(27)
                        .timeUnit(MONTH)
                        .build()
        );

        //when
        final boolean validationResult = retentionTime.isValid();

        //then
        assertFalse(validationResult);

    }

    @Test
    void listRetentionShouldNotBeValidWhenInf1M() {
        //given
        final ListRetention retentionTime = ListRetention.buildListRetention(
                ListRetentionWithExpiration.builder()
                        .number(0)
                        .timeUnit(MONTH)
                        .build()
        );

        //when
        final boolean validationResult = retentionTime.isValid();

        //then
        assertTrue(validationResult);

    }
}