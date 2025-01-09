package kr.hhplus.be.server.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItemDto {
    private String productId;
    private Integer quantity;
}
