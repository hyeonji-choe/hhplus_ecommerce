package kr.hhplus.be.server.domain.order.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.order.OrderState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "order")
@ToString
@NoArgsConstructor
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


    @Builder
    public Order(Long userId, Long totalPrice) {
        this.orderState = OrderState.PEND;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }

}
