package io.nagurea.smsupsdk.sendsms.common;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Recipients {
    private final Set<Gsm> gsm;

    public void check(){
        gsm.forEach(Gsm::check);
    }
}
