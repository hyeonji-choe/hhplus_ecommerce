package kr.hhplus.be.server.infrastructure;

import kr.hhplus.be.server.domain.EventState;
import kr.hhplus.be.server.domain.payment.entity.PaymentEventOutBox;
import kr.hhplus.be.server.domain.payment.repository.PaymentEventOutBoxRepository;
import kr.hhplus.be.server.infrastructure.repository.PaymentEventOutBoxJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentEventOutBoxRepositoryImpl implements PaymentEventOutBoxRepository {
    private final PaymentEventOutBoxJpaRepository paymentEventOutBoxJpaRepository;

    public PaymentEventOutBoxRepositoryImpl(PaymentEventOutBoxJpaRepository paymentEventOutBoxJpaRepository) {
        this.paymentEventOutBoxJpaRepository = paymentEventOutBoxJpaRepository;
    }

    @Override
    public PaymentEventOutBox findByPaymentId(Long paymentId) {
        return paymentEventOutBoxJpaRepository.findPaymentEventOutBoxByPaymentId(paymentId);
    }

    @Override
    public PaymentEventOutBox save(PaymentEventOutBox paymentEventOutBox) {
        return paymentEventOutBoxJpaRepository.save(paymentEventOutBox);
    }

    @Override
    public List<PaymentEventOutBox> findAllByState(EventState state) {
        return paymentEventOutBoxJpaRepository.findAllByEventState(state);
    }

}