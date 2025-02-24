package kr.hhplus.be.server.interfaces.kafka;


import kr.hhplus.be.server.application.payment.PaymentOutboxService;
import kr.hhplus.be.server.domain.EventState;
import kr.hhplus.be.server.interfaces.event.OrderPaidEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final PaymentOutboxService paymentOutboxService;

    public void create() {
        kafkaTemplate.send("topic", "say hello~!!!!");
    }

    /**
     * 카프카 이벤트를 발행한다.
     * - 카프카 발행이 성공한다면 (WhenComplete), OutBox의 상태를 PUBLISHED 로 변경한다.
     */
    public void publishOrderInfo(OrderPaidEvent event) {
        kafkaTemplate.send("payment-event", event.getPaymentId().toString())
                .whenComplete((result, error) -> {
                    if (error == null) {
                        log.info("Kafka payment event published");
                        paymentOutboxService.updateEventState(event.getPaymentId(), EventState.PUBLISH);
                    }
                });

    }
}