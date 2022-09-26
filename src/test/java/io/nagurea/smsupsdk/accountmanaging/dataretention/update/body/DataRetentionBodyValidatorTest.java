package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.CampaignRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetentionWithExpiration;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.MessageRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.SurveyRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.ValidRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.DataRetentionBodyValidator;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.DataRetentionInfoValidator;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.RetentionTimeValidator;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.validator.RetentionValidator;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.TimeUnit.MONTH;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DataRetentionBodyValidatorTest {

    @Test
    void validates() {
        // Given
        final DataRetentionInfo dataRetentionInfo = DataRetentionInfo.builder()
                .message(MessageRetention.builder()
                        .number(2)
                        .timeUnit(TimeUnit.DAY).build())
                .survey(SurveyRetention.builder()
                        .number(5)
                        .timeUnit(MONTH)
                        .build())
                .list(ListRetention.buildListRetention(
                        ListRetentionWithExpiration.builder()
                                .number(2)
                                .timeUnit(MONTH).build()
                ))
                .campaign(CampaignRetention.builder()
                        .number(5)
                        .timeUnit(MONTH)
                        .build())
                .build();


        final DataRetentionBody dataRetentionBody = DataRetentionBody.builder().retention(dataRetentionInfo).build();

        // When
        final Set<ConstraintViolation<DataRetentionBody>> violations = DataRetentionBodyValidator.validates(dataRetentionBody);


        //Then
        assertEquals(0, violations.size());
    }

    @Test
    void doNotValidateMessage() {
        // Given
        final DataRetentionInfo dataRetentionInfo = DataRetentionInfo.builder()
                .message(MessageRetention.builder()
                        .number(7) // illegal
                        .timeUnit(MONTH).build())
                .survey(SurveyRetention.builder()
                        .number(26)
                        .timeUnit(MONTH)
                        .build())
                .list(ListRetention.buildListRetention(
                        ListRetentionWithExpiration.builder()
                                .number(26)
                                .timeUnit(MONTH).build()
                ))
                .campaign(CampaignRetention.builder()
                        .number(0) // must be > 0
                        .timeUnit(MONTH)
                        .build())
                .build();


        final DataRetentionBody dataRetentionBody = DataRetentionBody.builder().retention(dataRetentionInfo).build();

        // When
        final Set<ConstraintViolation<DataRetentionBody>> violationsRetentionBody = DataRetentionBodyValidator.validates(dataRetentionBody);
        final Set<ConstraintViolation<DataRetentionInfo>> violationsRetentionInfo = DataRetentionInfoValidator.validates(dataRetentionBody.getRetention());
        final Set<ConstraintViolation<ValidRetention>> violationsRetention = RetentionValidator.validates(dataRetentionBody.getRetention().getCampaign());
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getSurvey()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getMessage()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getList()));

        //Then
        assertEquals(2, violationsRetentionBody.size());
        assertEquals(2, violationsRetentionInfo.size());
        assertEquals(2, violationsRetention.size());
    }
    @Test
    void doNotValidateSurvey() {
        // Given
        final DataRetentionInfo dataRetentionInfo = DataRetentionInfo.builder()
                .message(MessageRetention.builder()
                        .number(6)
                        .timeUnit(MONTH).build())
                .survey(SurveyRetention.builder()
                        .number(27)// illegal
                        .timeUnit(MONTH)
                        .build())
                .list(ListRetention.buildListRetention(
                        ListRetentionWithExpiration.builder()
                                .number(26)
                                .timeUnit(MONTH).build()
                ))
                .campaign(CampaignRetention.builder()
                        .number(0) // must be > 0
                        .timeUnit(MONTH)
                        .build())
                .build();


        final DataRetentionBody dataRetentionBody = DataRetentionBody.builder().retention(dataRetentionInfo).build();

        // When
        final Set<ConstraintViolation<DataRetentionBody>> violationsRetentionBody = DataRetentionBodyValidator.validates(dataRetentionBody);
        final Set<ConstraintViolation<DataRetentionInfo>> violationsRetentionInfo = DataRetentionInfoValidator.validates(dataRetentionBody.getRetention());
        final Set<ConstraintViolation<ValidRetention>> violationsRetention = RetentionValidator.validates(dataRetentionBody.getRetention().getCampaign());
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getSurvey()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getMessage()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getList()));

        //Then
        assertEquals(2, violationsRetentionBody.size());
        assertEquals(2, violationsRetentionInfo.size());
        assertEquals(2, violationsRetention.size());
    }
    @Test
    void doNotValidateList() {
        // Given
        final DataRetentionInfo dataRetentionInfo = DataRetentionInfo.builder()
                .message(MessageRetention.builder()
                        .number(6)
                        .timeUnit(MONTH).build())
                .survey(SurveyRetention.builder()
                        .number(26)
                        .timeUnit(MONTH)
                        .build())
                .list(ListRetention.buildListRetention(
                        ListRetentionWithExpiration.builder()
                                .number(27)  // illegal
                                .timeUnit(MONTH).build()
                ))
                .campaign(CampaignRetention.builder()
                        .number(0) // must be > 0
                        .timeUnit(MONTH)
                        .build())
                .build();


        final DataRetentionBody dataRetentionBody = DataRetentionBody.builder().retention(dataRetentionInfo).build();

        // When
        final Set<ConstraintViolation<DataRetentionBody>> violationsRetentionBody = DataRetentionBodyValidator.validates(dataRetentionBody);
        final Set<ConstraintViolation<DataRetentionInfo>> violationsRetentionInfo = DataRetentionInfoValidator.validates(dataRetentionBody.getRetention());
        final Set<ConstraintViolation<ValidRetention>> violationsRetention = RetentionValidator.validates(dataRetentionBody.getRetention().getCampaign());
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getSurvey()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getMessage()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getList()));

        //Then
        assertEquals(2, violationsRetentionBody.size());
        assertEquals(2, violationsRetentionInfo.size());
        assertEquals(2, violationsRetention.size());
    }
    @Test
    void doNotValidateCampaign() {
        // Given
        final DataRetentionInfo dataRetentionInfo = DataRetentionInfo.builder()
                .message(MessageRetention.builder()
                        .number(6)
                        .timeUnit(MONTH).build())
                .survey(SurveyRetention.builder()
                        .number(26)
                        .timeUnit(MONTH)
                        .build())
                .list(ListRetention.buildListRetention(
                        ListRetentionWithExpiration.builder()
                                .number(26)
                                .timeUnit(MONTH).build()
                ))
                .campaign(CampaignRetention.builder()
                        .number(27) // illegal
                        .timeUnit(MONTH)
                        .build())
                .build();


        final DataRetentionBody dataRetentionBody = DataRetentionBody.builder().retention(dataRetentionInfo).build();

        // When
        final Set<ConstraintViolation<DataRetentionBody>> violationsRetentionBody = DataRetentionBodyValidator.validates(dataRetentionBody);
        final Set<ConstraintViolation<DataRetentionInfo>> violationsRetentionInfo = DataRetentionInfoValidator.validates(dataRetentionBody.getRetention());
        final Set<ConstraintViolation<ValidRetention>> violationsRetention = RetentionValidator.validates(dataRetentionBody.getRetention().getCampaign());
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getSurvey()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getMessage()));
        violationsRetention.addAll(RetentionValidator.validates(dataRetentionBody.getRetention().getList()));

        //Then
        assertEquals(1, violationsRetentionBody.size());
        assertEquals(1, violationsRetentionInfo.size());
        assertEquals(1, violationsRetention.size());
    }


}