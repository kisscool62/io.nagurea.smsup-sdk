package io.nagurea.smsupsdk.lists.get.list;

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
public class GetListResultResponse extends ResultResponse {
    private final List<Contact> list;

    /**
     * Ex: "Liste API"
     */
    private final String name;

    /**
     * Ex: "5"
     */
    private final String contacts;

    /**
     * Ex: 5
     */
    private final Integer totalRecords;

    /**
     * Ex: 5
     */
    private final Integer totalDisplayRecords;

    @Builder
    public GetListResultResponse(ResponseStatus responseStatus, String message, List<Contact> list, String name, String contacts, Integer totalRecords, Integer totalDisplayRecords) {
        super(responseStatus, message);
        this.list = list;
        this.name = name;
        this.contacts = contacts;
        this.totalRecords = totalRecords;
        this.totalDisplayRecords = totalDisplayRecords;
    }


}
