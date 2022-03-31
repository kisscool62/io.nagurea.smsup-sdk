package io.nagurea.smsupsdk.common.response;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ResultResponse {

    private final ResponseStatus responseStatus;

    private final String message;

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

    public class ResultResponseBuilder{

        private ResponseStatus status;

        public ResultResponseBuilder status(int status){
            this.status = ResponseStatus.findByCode(status);
            return this;
        }
    }


}
