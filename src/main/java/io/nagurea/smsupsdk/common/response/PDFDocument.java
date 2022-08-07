package io.nagurea.smsupsdk.common.response;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.ByteArrayOutputStream;

import static io.nagurea.smsupsdk.helper.MD5Helper.md5sum;

@Getter
public class PDFDocument extends Document<ByteArrayOutputStream>{

    private final String filename;

    @Builder
    public PDFDocument(ByteArrayOutputStream documentOutputStream, String filename) {
        super(documentOutputStream);
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "PDFDocument{" +
                "documentOutputStream=" + md5sum(documentOutputStream) +
                ", filename='" + filename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PDFDocument that = (PDFDocument) o;

        return new EqualsBuilder().append(filename, that.filename)
                .append(md5sum(documentOutputStream), md5sum(that.documentOutputStream))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(filename)
                .append(md5sum(documentOutputStream))
                .toHashCode();
    }


}
