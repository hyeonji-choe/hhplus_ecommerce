package kr.hhplus.be.server.application.payment;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.domain.EventState;
import kr.hhplus.be.server.domain.payment.entity.PaymentEventOutBox;
import kr.hhplus.be.server.domain.payment.repository.PaymentEventOutBoxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentOutboxServiceImpl implements PaymentOutboxService {

    private final PaymentEventOutBoxRepository paymentEventOutBoxRepository;

    @Override
    public void published(Long paymentId) {
        updateEventState(paymentId, EventState.PUBLISH);
    }

    @Override
    public void updateEventState(Long paymentId, EventState eventState) {
        PaymentEventOutBox paymentEventOutBox = paymentEventOutBoxRepository.findByPaymentId(paymentId);
        if (ObjectUtils.isEmpty(paymentEventOutBox)) throw new EntityNotFoundException("PaymentOutbox not found.");
        paymentEventOutBox.updateState(eventState);
        paymentEventOutBoxRepository.save(paymentEventOutBox);
    }
}
