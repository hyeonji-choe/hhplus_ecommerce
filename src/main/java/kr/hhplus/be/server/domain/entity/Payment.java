package kr.hhplus.be.server.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "payment")
@ToString
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", insertable = false, nullable = false)
    private Long id;
    @Column(name = "total_payment_price")
    private long totalPaymentPrice;
    @Column(name = "payment_state")
    private PaymentState paymentState;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToOne
    @JoinColumn(name = "coupon_history_id")
    private CouponHistory couponHistory;
}
