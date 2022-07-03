package io.nagurea.smsupsdk.lists.blacklist;

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
public class BlackistResultResponse extends ResultResponse {
    private final List<BlacklistedContact> list;

    /**
     * Ex: 5
     */
    private final Integer totalRecords;

    /**
     * Ex: 5
     */
    private final Integer totalDisplayRecords;

    @Builder
    public BlackistResultResponse(ResponseStatus responseStatus, String message, List<BlacklistedContact> list, Integer totalRecords, Integer totalDisplayRecords) {
        super(responseStatus, message);
        this.list = list;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalDisplayRecords;
    }


}
