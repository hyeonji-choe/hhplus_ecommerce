package kr.hhplus.be.server.domain.payment.repository;

import kr.hhplus.be.server.domain.EventState;
import kr.hhplus.be.server.domain.payment.entity.PaymentEventOutBox;

import java.util.List;

public interface PaymentEventOutBoxRepository {
    PaymentEventOutBox findByPaymentId(Long paymentId);

    PaymentEventOutBox save(PaymentEventOutBox paymentEventOutBox);

    List<PaymentEventOutBox> findAllByState(EventState state);
}
