package io.nagurea.smsupsdk.sendmessages.campaign.body;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Builder
public class Gsm {
    private final String gsmsmsid;
    private final String value;

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
