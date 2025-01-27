package kr.hhplus.be.server.application.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductServiceRequest {
    private Long orderId;
    private Long productId;
    private String productName;
    private Long productPrice;
    private Long quantity;
    private Long totalPrice;

}