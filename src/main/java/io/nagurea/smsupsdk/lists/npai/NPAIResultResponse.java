package io.nagurea.smsupsdk.lists.npai;

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
public class NPAIResultResponse extends ResultResponse {
    private final List<NPAIContact> list;

    /**
     * Ex: 5
     */
    private final Integer totalRecords;

    /**
     * Ex: 5
     */
    private final Integer totalDisplayRecords;

    @Builder
    public NPAIResultResponse(ResponseStatus responseStatus, String message, List<NPAIContact> list, Integer totalRecords, Integer totalDisplayRecords) {
        super(responseStatus, message);
        this.list = list;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalDisplayRecords;
    }


}
