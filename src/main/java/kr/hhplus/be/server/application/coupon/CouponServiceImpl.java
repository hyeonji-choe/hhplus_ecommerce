package kr.hhplus.be.server.application.coupon;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.CouponResult;
import kr.hhplus.be.server.api.model.IssueCouponResult;
import kr.hhplus.be.server.common.exception.CouponErrorCode;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.coupon.CouponIssuer;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.respository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.coupon.respository.CouponRepository;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponHistoryRepository couponHistoryRepository;
    private final UserRepository userRepository;
    private final CouponIssuer couponIssuer;

    @Override
    public CouponResult registerCoupon(String couponName, int quantity, int discountRate) {
        Coupon coupon = couponRepository.save(Coupon.create(couponName, quantity, discountRate));
        log.debug("coupon_getId " + coupon.getId());
        return CouponResult.toResult(coupon);
    }

    @Override
    @Transactional
    public CouponResult getCouponInfoByCouponId(Long couponId) {
        Coupon coupon = couponRepository.findByCouponIdWithLock(couponId);
        if (coupon == null)
            throw new EntityNotFoundException("Coupon not found.");

        return CouponResult.toResult(coupon);
    }

    @Override
    public Page<CouponResult> getCouponList(Pageable pageable) {
        Page<Coupon> coupons = couponRepository.findAll(pageable);

        List<CouponResult> couponResults = coupons.getContent().stream()
                .map(coupon -> {
                    return CouponResult.toResult(coupon);
                }).toList();
        return new PageImpl<>(couponResults, pageable, coupons.getTotalElements());
    }

    @Override
    @Transactional
    public IssueCouponResult issueCoupon(Long couponId, Long userId) throws CustomException {
        User user = userRepository.findByUserIdWithLock(userId);
        if (user == null) throw new EntityNotFoundException("User not found.");

        Coupon coupon = couponRepository.findByCouponIdWithLock(couponId);
        if (coupon == null) throw new EntityNotFoundException("Coupon not found.");

        Coupon issuedCoupon = couponIssuer.issue(user, coupon);

        couponRepository.save(issuedCoupon);
        CouponHistory history = couponHistoryRepository.save(CouponHistory.create(HistoryType.ISSUE, LocalDateTime.now(), userId, couponId));

        return IssueCouponResult.create(history.getId(), coupon.getId(), user.getId(), coupon.getCouponName(), "success");
    }

    @Override
    @Transactional
    public IssueCouponResult issueCouponWithOptimisticLock(Long couponId, Long userId) throws CustomException {
        User user = userRepository.findByUserIdWithLock(userId);
        if (user == null) throw new EntityNotFoundException("User not found.");

        Coupon coupon = couponRepository.findByCouponIdWithOptimisticLock(couponId);
        if (coupon == null) throw new EntityNotFoundException("Coupon not found.");

        Coupon issuedCoupon = couponIssuer.issue(user, coupon);

        couponRepository.save(issuedCoupon);
        CouponHistory history = couponHistoryRepository.save(CouponHistory.create(HistoryType.ISSUE, LocalDateTime.now(), userId, couponId));

        return IssueCouponResult.create(history.getId(), coupon.getId(), user.getId(), coupon.getCouponName(), "success");
    }

    @Override
    @Transactional
    public IssueCouponResult useUserIssuedCoupon(Long userId, Long couponId) throws CustomException {
        Coupon coupon = couponRepository.findByCouponIdWithLock(couponId);
        if (coupon == null) throw new EntityNotFoundException("Coupon not found.");

        User user = userRepository.findByUserIdWithLock(userId);
        if (user == null) throw new EntityNotFoundException("User not found.");

        List<CouponHistory> history = couponHistoryRepository.findByCouponIdAndUserId(couponId, userId);
        if (history == null) throw new EntityNotFoundException("Coupon history not found.");
        if (history.stream().anyMatch(h -> h.getType().equals(HistoryType.USE)))
            throw new CustomException(CouponErrorCode.ALREADY_USED_COUPON);

        CouponHistory issuedCoupon = couponHistoryRepository.save(CouponHistory.create(HistoryType.USE, LocalDateTime.now(), userId, couponId));

        return IssueCouponResult.toResult(coupon, issuedCoupon);
    }

    @Transactional(readOnly = true)
    @Override
    public List<IssueCouponResult> userIssuedCoupons(Long userId) {
        User user = userRepository.findByUserIdWithLock(userId);
        if (user == null) throw new EntityNotFoundException("User not found.");

        // 사용자 쿠폰 목록 조회
        List<CouponHistory> userCoupoonList = couponHistoryRepository.findByUserId(userId);

        return userCoupoonList.stream()
                .map(history -> {
                    Coupon coupon = couponRepository.findByCouponId(history.getCouponId());
                    return IssueCouponResult.toResult(coupon, history);
                })
                .toList();
    }
}
