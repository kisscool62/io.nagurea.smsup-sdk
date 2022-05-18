package io.nagurea.smsupsdk.sendsms.common;

import io.nagurea.smsupsdk.common.response.ResultResponse;
import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class SendSMSResponse extends ResultResponse {


    /**
     * The id of your campaign
     */
    private final String ticket;

    /**
     * The cost of your campaign
     */
    private final Integer cost;

    /**
     * Your credits after your campaign has been created
     */
    private final Integer credits;

    /**
     * Number of message before filtering
     */
    private final Integer total;

    /**
     * Number of message after filtering
     */
    private final Integer sent;

    /**
     * Number of blacklisted numbers
     */
    private final Integer blacklisted;

    /**
     * Number of duplicated numbers
     */
    private final Integer duplicated;

    /**
     * Number of invalid numbers
     */
    private final Integer invalid;

    /**
     * Number of npai numbers
     */
    private final Integer npai;



    protected SendSMSResponse(ResponseStatus responseStatus, String message, String ticket, Integer cost, Integer credits, Integer total, Integer sent, Integer blacklisted, Integer duplicated, Integer invalid, Integer npai) {
        super(responseStatus, message);
        this.ticket = ticket;
        this.cost = cost;
        this.credits = credits;
        this.total = total;
        this.sent = sent;
        this.blacklisted = blacklisted;
        this.duplicated = duplicated;
        this.invalid = invalid;
        this.npai = npai;
    }

}
