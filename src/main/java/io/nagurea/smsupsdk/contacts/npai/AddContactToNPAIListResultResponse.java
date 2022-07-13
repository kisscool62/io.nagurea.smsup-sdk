package io.nagurea.smsupsdk.contacts.npai;

import com.google.gson.annotations.SerializedName;
import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Getter
@ToString(callSuper = true)
public class AddContactToNPAIListResultResponse extends ResultResponse {

    @SerializedName("added_contacts")
    private final Integer addedContacts;

    @Builder
    public AddContactToNPAIListResultResponse(
            ResponseStatus responseStatus,
            String message, Integer addedContacts) {
        super(responseStatus, message);
        this.addedContacts = addedContacts;
    }

}
