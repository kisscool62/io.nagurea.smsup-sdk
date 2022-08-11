package io.nagurea.smsupsdk.apitoken.retrieve;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class RetrieveTokensResultResponse extends ResultResponse {
    private final List<TokenInfo> tokens;

    @Builder
    public RetrieveTokensResultResponse(ResponseStatus responseStatus, String message, List<TokenInfo> tokens) {
        super(responseStatus, message);
        this.tokens = tokens;
    }


}
