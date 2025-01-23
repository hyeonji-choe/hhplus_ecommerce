package kr.hhplus.be.server.application.payment;

import kr.hhplus.be.server.api.model.PaymentResult;

public interface PaymentService {
    PaymentResult createPayment(PaymentServiceRequest request);
}
