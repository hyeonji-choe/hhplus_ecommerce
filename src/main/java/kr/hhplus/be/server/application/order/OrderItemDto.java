package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.domain.order.entity.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemDto {
    private Long orderProductId;
    private Long orderId;
    private Long productId;
    private String productName;
    private Double productPrice;
    private int quantity;
    private Long totalPrice;

    public static OrderItemDto toDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .orderProductId(orderItem.getProductId())
                .orderId(orderItem.getOrderId())
                .productId(orderItem.getProductId())
                .productName(orderItem.getProduct().getProductName())
                .productPrice(orderItem.getProduct().getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }
}