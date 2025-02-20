package kr.hhplus.be.server.interfaces.event;

import kr.hhplus.be.server.domain.payment.entity.PaymentEventOutBox;
import kr.hhplus.be.server.domain.payment.repository.PaymentEventOutBoxRepository;
import kr.hhplus.be.server.interfaces.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderPaidEventListener {
    private final KafkaProducer kafkaProducer;
    private final PaymentEventOutBoxRepository paymentEventOutBoxRepository;

    // outbox 저장
    @TransactionalEventListener(phase = BEFORE_COMMIT)
    public void saveOutbox(OrderPaidEvent event) {
        paymentEventOutBoxRepository.save(PaymentEventOutBox.create(event.getPaymentId()));
    }

    // 메시지 발행
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void sendOrderPaidInfo(OrderPaidEvent event) {
        log.info("주문 완료 이벤트 수신 (paymentId: {}) - 트랜잭션 커밋 후 실행", event.getPaymentId());
        kafkaProducer.publishOrderInfo(event);
    }
}
