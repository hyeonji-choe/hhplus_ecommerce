package kr.hhplus.be.server.application.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceRequest {
    private Long userId;
    private Long issuanceId;
    private Long totalPrice;
    private OrderStateType orderState;

    public void changeOrderStatus(OrderStateType orderState) {
        this.orderState = orderState;
    }
}