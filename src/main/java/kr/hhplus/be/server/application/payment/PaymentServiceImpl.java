package kr.hhplus.be.server.application.payment;

import kr.hhplus.be.server.api.model.PaymentResult;
import kr.hhplus.be.server.domain.payment.entity.Payment;
import kr.hhplus.be.server.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentResult createPayment(PaymentServiceRequest request) {
        Payment payment = request.toEntity();
        Payment savedPayment = paymentRepository.save(payment);

        return PaymentResult.toResult(savedPayment);
    }
}
