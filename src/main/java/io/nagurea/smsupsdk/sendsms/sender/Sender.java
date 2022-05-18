package io.nagurea.smsupsdk.sendsms.sender;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Sender {

    private final String who;

    public static Sender build(final String who){
        return new Sender(who);
    }

    public Optional<String> whoSent(){
        return Optional.ofNullable(who);
    }
}
