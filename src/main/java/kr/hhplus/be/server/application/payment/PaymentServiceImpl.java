package kr.hhplus.be.server.application.payment;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.PaymentResult;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import kr.hhplus.be.server.interfaces.event.OrderPaidEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final ApplicationEventPublisher eventPublisher;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PaymentResult createPayment(PaymentServiceRequest request) {
        Payment payment = request.toEntity();

        Order order = orderRepository.findByOrderId(payment.getOrderId());
        if (ObjectUtils.isEmpty(order)) throw new EntityNotFoundException("Order not found.");

        User user = userRepository.findByUserId(order.getUserId());
        if (ObjectUtils.isEmpty(user)) throw new EntityNotFoundException("User not found.");
        // 유저 포인트 차감
        user.chargeAsset(payment.getTotalPaymentPrice());
        userRepository.save(user);
        // 주문 상태 변경
        order.orderComplete();
        orderRepository.save(order);
        // 결제 정보 저장
        Payment savedPayment = paymentRepository.save(payment);

        eventPublisher.publishEvent(OrderPaidEvent.of(savedPayment.getId()));

        return PaymentResult.toResult(savedPayment);
    }
}
