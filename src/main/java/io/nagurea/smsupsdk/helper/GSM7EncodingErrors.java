package io.nagurea.smsupsdk.helper;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class GSM7EncodingErrors {

    @Getter
    private final List<String> characterErrors = new ArrayList<>();

    public void add(String character){
        characterErrors.add(character);
    }
}
