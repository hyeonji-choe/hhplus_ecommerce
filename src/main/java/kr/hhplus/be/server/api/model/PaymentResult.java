package kr.hhplus.be.server.api.model;

import kr.hhplus.be.server.domain.payment.PaymentState;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import lombok.Builder;

@Builder
public class PaymentResult {
    private Long paymentId;
    private Long orderId;
    private Long totalPaymentPrice;
    private PaymentState paymentState;

    public static PaymentResult toResult(Payment payment) {
        return PaymentResult.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .totalPaymentPrice(payment.getTotalPaymentPrice())
                .paymentState(payment.getPaymentState())
                .build();
    }
}
