package org.agzw.brick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static org.agzw.brick.constant.CommonConstant.DOUBLE_QUOTES;
import static org.agzw.brick.constant.CommonConstant.SEPARATOR;

@AllArgsConstructor
@Builder
@Data
@JsonPropertyOrder({"productName", "description", "imageLink", "price", "rating", "merchant"})
public class Product {
    private String productName;
    private String description;
    private String imageLink;
    private double price;
    private double rating;
    private String merchant;

    public String toString() {
        return productName + SEPARATOR + DOUBLE_QUOTES + description + DOUBLE_QUOTES + SEPARATOR + imageLink + SEPARATOR + price + SEPARATOR + rating + "/5" + SEPARATOR + merchant;
    }

    public abstract static class ProductFormat {

        @JsonProperty("Product Name")
        abstract String getProductName();

        @JsonProperty("Description")
        abstract String getDescription();

        @JsonProperty("Image Link")
        abstract String getImageLink();

        @JsonProperty("Price")
        abstract String getPrice();

        @JsonProperty("Rating")
        abstract String getRating();

        @JsonProperty("Merchant Name")
        abstract String getMerchant();
    }
}
