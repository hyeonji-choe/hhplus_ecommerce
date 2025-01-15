package kr.hhplus.be.server.api.model;

import kr.hhplus.be.server.application.order.OrderItemDto;
import kr.hhplus.be.server.domain.order.entity.Order;
import lombok.Builder;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
public class OrderResult {

    private Long orderId;
    private Long userId;
    private Long totalPrice;
    private List<OrderItemDto> orderItemList;

    public static OrderResult toResult(Order order) {
        return OrderResult.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}
