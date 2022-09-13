package io.nagurea.smsupsdk.accountmanaging.account.create.body;

import com.google.gson.annotations.SerializedName;
import com.neovisionaries.i18n.CountryCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static io.nagurea.smsupsdk.accountmanaging.account.create.body.Childness.MAIN;
import static io.nagurea.smsupsdk.accountmanaging.account.create.body.Childness.SUB_ACCOUNT;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class AccountInfo {

    /**
     * The email of the account
     */
    @NotNull
    @Email
    private final String email; // required

    /**
     * The password must be at least 6 characters long (25 max)
     */
    @NotNull
    @Size(min = 6, max = 25, message = "The password must be at least 6 characters long (25 max)")
    private final String password; // required

    /**
     * The country code associated to the account (ISO 3166-1 alpha-2)
     */
    @SerializedName("country_code")
    @NotNull(groups = MainAccount.class)
    private final CountryCode countryCode; //required if main account

    /**
     * 	The firstname associated to the account
     */
    private final String firstname;

    /**
     * 	The lastname associated to the account
     */
    private final String lastname;

    /**
     * 	The city associated to the account
     */
    private final String city;

    /**
     * 	The phone number associated to the account
     */
    private final String phone;

    /**
     * 	The address associated to the account
     */
    private final String address1;

    /**
     * 	Further information about the address
     */
    private final String address2;

    /**
     * 	The zip code
     */
    private final String zip;

    /**
     * 	The company associated to the account
     */
    private final String company;

    /**
     * Select one between : company, association, administration, private
     */
    private final AccountType type;

    /**
     * The default sender that will be used for your sendings
     */
    private final String sender;

    /**
     * 	Feel free to write anything about this account
     */
    private final String description;

    /**
     * 	integer 0 for a main account, 1 for a sub-account
     */
    private final Childness isChild;

    /**
     * Is the account unlimited ? If unlimited, the sub-account uses the parent's credits. If not, the main account has to give a certain amount of credits to its sub-account.
     */
    @NotNull(groups = SubAccount.class)
    private final Integer unlimited; //required if isChild

    /**
     * Returns true if isChild is main account. return false else. Not main account does not means sub account because isChild is not required
     * @return true if isChild is main account. return false else.
     */
    public boolean isMainAccount(){
        return MAIN.equals(isChild);
    }

    /**
     * Returns true if isChild is sub account. return false else. Not sub account does not means main account because isChild is not required
     * @return true if isChild is sub account. return false else.
     */
    public boolean isSubAccount(){
        return SUB_ACCOUNT.equals(isChild);
    }

}
