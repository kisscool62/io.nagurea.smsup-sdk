package io.nagurea.smsupsdk.accountmanaging.transfercredits.body;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class TransferInfo {

    @SerializedName("to_account")
    private final Integer toAccount;

    private final Integer credits;

}
