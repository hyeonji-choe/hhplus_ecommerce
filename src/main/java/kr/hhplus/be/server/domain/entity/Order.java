package kr.hhplus.be.server.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Payment payment;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    public Order(User user, List<OrderItem> orderItems) {
        this.orderState = OrderState.PEND;
        this.user = user;
        this.orderItems = orderItems;
        this.totalPrice = orderItems.stream().map(o -> o.getProduct().getPrice()).reduce(0L, Long::sum);
    }

}
