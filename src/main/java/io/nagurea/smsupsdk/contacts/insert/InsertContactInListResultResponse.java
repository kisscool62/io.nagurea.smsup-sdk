package io.nagurea.smsupsdk.contacts.insert;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class InsertContactInListResultResponse extends ResultResponse {

    private final Integer contacts;
    private final String id; //the id of the created list

    @Builder
    public InsertContactInListResultResponse(
            ResponseStatus responseStatus,
            String message, Integer contacts, String id) {
        super(responseStatus, message);
        this.contacts = contacts;
        this.id = id;
    }

}
