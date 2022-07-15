package io.nagurea.smsupsdk.invoices.get;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Invoice {
    private final String id;
    private final String name;
    private final LocalDateTime creation;
    private final LocalDateTime validation;
    private final String price;
    private final String vat;
    private final String total;
    private final String currency;
    private final String status;

    @SerializedName("payment_type")
    private final String paymentType;

    @Singular
    private final List<Product> products;

}
