package io.nagurea.smsupsdk.sendmessages.sender;

import java.util.Optional;

public class NoSender extends Sender{

    private NoSender() {
        super(null);
    }

    public static NoSender build(){
        return new NoSender();
    }


    @Override
    public Optional<String> whoSent() {
        return Optional.empty();
    }
}
