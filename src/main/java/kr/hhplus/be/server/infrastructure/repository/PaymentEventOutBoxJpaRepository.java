package kr.hhplus.be.server.infrastructure.repository;

import kr.hhplus.be.server.domain.EventState;
import kr.hhplus.be.server.domain.payment.entity.PaymentEventOutBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentEventOutBoxJpaRepository extends JpaRepository<PaymentEventOutBox, Long> {
    PaymentEventOutBox findPaymentEventOutBoxByPaymentId(Long paymentId);

    PaymentEventOutBox save(PaymentEventOutBox paymentEventOutBox);

    List<PaymentEventOutBox> findAllByEventState(EventState eventState);
}
