package kr.hhplus.be.server.interfaces.kafka;

import kr.hhplus.be.server.application.payment.PaymentOutboxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final PaymentOutboxService paymentOutboxService;

    @KafkaListener(topics = "topic", groupId = "group_1")
    public void listen(String message) {
        log.info("Received Messasge in group group_1: " + message);
    }

    // outbox 수정
    @KafkaListener(topics = "payment-event", groupId = "payment-group")
    public void consume(String paymentId) {
        log.info("Kafka Listen (paymentId: {})", paymentId);
        paymentOutboxService.published(Long.parseLong(paymentId));
    }
}