package io.nagurea.smsupsdk.lists.create.body;

import io.nagurea.smsupsdk.common.e164.E164Helper;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Gsm {

    /**
     * Ex: 41781234567 in E164 format
     */
    private final String value;

    /**
     * Louis
     */
    private final String info1;

    /**
     * de Broglie
     */
    private final String info2;

    /**
     * 1892
     */
    private final String info3;

    /**
     * Dieppe
     */
    private final String info4;

    public void check(){
        if(value == null){
            throw RequiredParameterException.builder().requiredParam("Contact/GSM::value", value).build();
        }
        E164Helper.check(value);
    }
}
