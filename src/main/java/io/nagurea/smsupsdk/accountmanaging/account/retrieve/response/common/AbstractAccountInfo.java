package io.nagurea.smsupsdk.accountmanaging.account.retrieve.response.common;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public abstract class AbstractAccountInfo {
     @SerializedName("client_id")
     private final String clientId;
     private final String email;
     private final String firstname;
     private final String lastname;
     private final String city;
     private final String phone;
     private final String address1;
     private final String address2;
     private final String zip;
     private final String country;

     @SerializedName("country_code")
     private final String countryCode;
     private final String lang;
     private final String credits;
     private final String unlimited;
     private final String description;
     private final String senderid;
     private final String status;
}
