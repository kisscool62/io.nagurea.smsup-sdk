package io.nagurea.smsupsdk.common.response;

import io.nagurea.smsupsdk.common.status.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class ResultResponse {

    private final ResponseStatus responseStatus;

    private final String message;


    public class ResultResponseBuilder{

        private ResponseStatus status;

        public ResultResponseBuilder status(int status){
            this.status = ResponseStatus.findByCode(status);
            return this;
        }
    }


}
