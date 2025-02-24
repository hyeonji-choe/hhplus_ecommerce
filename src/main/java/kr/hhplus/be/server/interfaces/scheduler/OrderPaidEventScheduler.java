package kr.hhplus.be.server.interfaces.scheduler;

import kr.hhplus.be.server.domain.EventState;
import kr.hhplus.be.server.domain.payment.repository.PaymentEventOutBoxRepository;
import kr.hhplus.be.server.interfaces.event.OrderPaidEvent;
import kr.hhplus.be.server.interfaces.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@RequiredArgsConstructor
@Component
public class OrderPaidEventScheduler {

    private final KafkaProducer kafkaProducer;
    private final PaymentEventOutBoxRepository paymentEventOutBoxRepository;

    // 5분간 메시지의 상태가 변하지 않았다면, 메시지 발행에 실패했다고 간주하고 메시지 재발행
    @Scheduled(fixedRate = 5000)
    public void rePublish() {
        paymentEventOutBoxRepository.findAllByState(EventState.INIT).forEach(it -> {
            if (it.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
                kafkaProducer.publishOrderInfo(OrderPaidEvent.of(it.getPaymentId()));
            }
        });
    }
}
