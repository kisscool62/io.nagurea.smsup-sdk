package io.nagurea.smsupsdk.invoices.get;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Product {

    private final String description;

    @SerializedName("unit_price")
    private final String unitPrice;

    private final String quantities;
    private final String price;
}
