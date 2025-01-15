package kr.hhplus.be.server.application.payment;

import kr.hhplus.be.server.domain.payment.PaymentState;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentServiceRequest {
    private Long orderId;
    private Long totalPaymentPrice;
    private PaymentState paymentState;

    public Payment toEntity() {
        return Payment.builder()
                .orderId(this.orderId)
                .totalPaymentPrice(this.totalPaymentPrice)
                .paymentState(this.paymentState)
                .build();
    }
}