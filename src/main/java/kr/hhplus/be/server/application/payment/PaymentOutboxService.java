package kr.hhplus.be.server.application.payment;

import kr.hhplus.be.server.domain.EventState;

public interface PaymentOutboxService {
    void published(Long paymentId);

    void updateEventState(Long paymentId, EventState eventState);
}
