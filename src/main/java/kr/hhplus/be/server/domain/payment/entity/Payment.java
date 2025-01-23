package kr.hhplus.be.server.domain.payment.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.payment.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "payment")
@Builder
@AllArgsConstructor
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
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "coupon_history_id")
    private Long couponHistoryId;

    public static Payment create(Long toatalPaymentPrice, PaymentState state, Long orderId) {
        return Payment.builder()
                .totalPaymentPrice(toatalPaymentPrice)
                .paymentState(state)
                .orderId(orderId)
                .build();
    }

    public void useCoupon(Long couponHistoryId) {
        this.couponHistoryId = couponHistoryId;
    }
}
