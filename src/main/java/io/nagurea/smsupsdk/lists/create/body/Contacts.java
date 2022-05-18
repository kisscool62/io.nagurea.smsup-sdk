package io.nagurea.smsupsdk.lists.create.body;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

@Builder
@Getter
public class Contacts {

    @Singular("gsm")
    private final @NonNull java.util.List<Gsm> gsm;

    public void check(){
        gsm.forEach(Gsm::check);
    }

}
