package kr.hhplus.be.server.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ProductResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("sale_price")
    private Double salePrice;

    @JsonProperty("stocks")
    private int stocks;

    public static ProductResult toResult(Product product) {
        return ProductResult.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .salePrice(product.getPrice())
                .stocks(product.getQuantity())
                .build();
    }

}