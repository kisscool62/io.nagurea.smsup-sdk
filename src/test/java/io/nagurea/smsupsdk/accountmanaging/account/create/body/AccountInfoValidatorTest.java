package io.nagurea.smsupsdk.accountmanaging.account.create.body;

import com.neovisionaries.i18n.CountryCode;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountInfoValidatorTest {

    @Test
    void validatesMainAccount() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("some password")
                .countryCode(CountryCode.DE)
                .isChild(Childness.MAIN)
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(0, validationElements.size());
    }

    @Test
    void doNotValidateMainAccount() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password(null) // required +1 validation error
                .countryCode(null) // required if main account +1 validation error
                .isChild(Childness.MAIN)
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(2, validationElements.size());
    }

    @Test
    void doNotValidateAccountBecauseOfPasswordTooLong() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("abcdefghijklmnopqrstuvwxyz") // required +1 validation error
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(1, validationElements.size());
    }

    @Test
    void doNotValidateAccountBecauseOfPasswordTooShort() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("abcde") // required +1 validation error
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(1, validationElements.size());
    }

    @Test
    void validatesAccountThanksOfPassword() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("abcdefg")
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(0, validationElements.size());
    }

    @Test
    void validatesAccountThanksOfPasswordAgain() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("abcdefghijklmnopqrstuvwxy")
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(0, validationElements.size());
    }

    @Test
    void doNotValidateAccountBecauseOfEmail() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("notAnEmail")
                .password("abcdefghijklmnopqrstuvwxy")
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(1, validationElements.size());
    }

    @Test
    void doNotValidateSubAccountBecauseOfUnlimitedRequired() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("abcdefghijklmnopqrstuvwxy")
                .isChild(Childness.SUB_ACCOUNT)
                .unlimited(null)
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(1, validationElements.size());
    }

    @Test
    void validatesSubAccount() {
        // Given
        AccountInfo accountInfo = AccountInfo.builder()
                .email("some@email")
                .password("abcdefghijklmnopqrstuvwxy")
                .isChild(Childness.SUB_ACCOUNT)
                .unlimited(0)
                .build();

        // When
        final Set<ConstraintViolation<AccountInfo>> validationElements = AccountInfoValidator.validates(accountInfo);

        // Then
        assertNotNull(validationElements);
        assertEquals(0, validationElements.size());
    }


}