package kr.hhplus.be.server.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.hhplus.be.server.domain.entity.Coupon;
import kr.hhplus.be.server.domain.entity.CouponHistory;
import kr.hhplus.be.server.domain.entity.HistoryType;
import kr.hhplus.be.server.domain.entity.User;
import kr.hhplus.be.server.domain.repository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.repository.CouponRepository;
import kr.hhplus.be.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponHistoryRepository couponHistoryRepository;

    @Override
    public void registerCoupon(String couponName, int quantity, int discountRate) {
        Coupon coupon = new Coupon(couponName, quantity, discountRate);
        couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public void getCoupon(Long couponId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found."));

        Coupon coupon = couponRepository.findById(couponId).orElseThrow(()
                -> new EntityNotFoundException("Coupon not found."));

        CouponHistory couponHistory = CouponHistory.builder()
                .user(user)
                .coupon(coupon)
                .issueUseDate(LocalDateTime.now())
                .type(HistoryType.ISSUE).build();

        coupon.getCoupon(couponHistory);
        couponHistoryRepository.save(couponHistory);
    }


}
