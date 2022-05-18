package io.nagurea.smsupsdk.lists.create;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
public class CreateListResultResponse extends ResultResponse {

    private final Integer contacts;
    private final String id;

    @Builder
    public CreateListResultResponse(ResponseStatus responseStatus, String message, Integer contacts, String id) {
        super(responseStatus, message);
        this.contacts = contacts;
        this.id = id;
    }


}
