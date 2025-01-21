package kr.hhplus.be.server.api.model;

import lombok.Getter;

@Getter
public class TopOrderProduct {
    private Long productId;
    private String productName;
    private Double salePrice;
    private int stocks;
    private Integer totalQuantity;
}
