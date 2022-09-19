package io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.account;

import io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.common.AbstractAccountInfo;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountInfo extends AbstractAccountInfo {
     private final String type;
     private final String company;

    @Builder
    public AccountInfo(String type, String company, String clientId, String email, String firstname, String lastname, String city, String phone, String address1, String address2, String zip, String country, String countryCode, String lang, String credits, String unlimited, String description, String senderid, String status) {
        super(clientId, email, firstname, lastname, city, phone, address1, address2, zip, country, countryCode, lang, credits, unlimited, description, senderid, status);
        this.type = type;
        this.company = company;
    }
}
