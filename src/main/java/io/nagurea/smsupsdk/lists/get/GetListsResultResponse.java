package io.nagurea.smsupsdk.lists.get;

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
public class GetListsResultResponse extends ResultResponse {
    private final List<ContactList> lists;

    @Builder
    public GetListsResultResponse(ResponseStatus responseStatus, String message, List<ContactList> lists) {
        super(responseStatus, message);
        this.lists = lists;
    }


}
