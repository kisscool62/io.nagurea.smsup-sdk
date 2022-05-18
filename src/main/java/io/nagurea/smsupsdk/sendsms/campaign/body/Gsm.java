package io.nagurea.smsupsdk.sendsms.campaign.body;

import io.nagurea.smsupsdk.common.e164.E164Helper;
import io.nagurea.smsupsdk.common.exception.RequiredParameterException;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Builder
public class Gsm {
    private final String gsmsmsid;
    private final String value;

    public void check(){
        if(value == null){
            throw RequiredParameterException.builder().requiredParam("Recipient/GSM::value", value).build();
        }
        E164Helper.check(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Gsm gsm = (Gsm) o;

        return new EqualsBuilder().append(gsmsmsid, gsm.gsmsmsid).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(gsmsmsid).toHashCode();
    }
}
