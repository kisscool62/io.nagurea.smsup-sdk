package io.nagurea.smsupsdk.accountmanaging.transfercredits.body;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class TransferBody {

    private final TransferInfo transfer;

}
