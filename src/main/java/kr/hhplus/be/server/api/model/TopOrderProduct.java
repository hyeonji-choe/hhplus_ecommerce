package kr.hhplus.be.server.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopOrderProduct {
    private Long productId;
    private String productName;
    private Double salePrice;
    private int stocks;
    private Integer totalQuantity;
}
