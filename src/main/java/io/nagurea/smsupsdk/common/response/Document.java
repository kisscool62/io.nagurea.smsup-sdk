package io.nagurea.smsupsdk.common.response;

import lombok.Getter;
import lombok.ToString;

import java.io.OutputStream;

@ToString
@Getter
public class Document<T extends OutputStream> {

    protected final T documentOutputStream;

    public Document(T documentOutputStream) {
        this.documentOutputStream = documentOutputStream;
    }
}
