package kr.hhplus.be.server.interfaces.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaidEvent {
    private Long paymentId;

    public static OrderPaidEvent of(Long paymentId) {
        return OrderPaidEvent
                .builder()
                .paymentId(paymentId)
                .build();
    }
}
