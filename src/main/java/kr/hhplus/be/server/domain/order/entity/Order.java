package kr.hhplus.be.server.domain.order.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.order.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "order_state")
    private OrderState orderState;

    @Column(name = "total_price")
    private long totalPrice;

    @Column(name = "user_id")
    private long userId;

    public static Order create(Long userId, Long totalPrice) {
        return Order.builder()
                .orderState(OrderState.PEND)
                .totalPrice(totalPrice)
                .userId(userId)
                .build();
    }

    public void orderComplete() {
        this.orderState = OrderState.COMPLETE;
    }

    public void orderCancle() {
        this.orderState = OrderState.CANCEL;
    }

}
