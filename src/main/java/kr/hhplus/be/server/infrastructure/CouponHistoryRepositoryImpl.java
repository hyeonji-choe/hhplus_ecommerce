package kr.hhplus.be.server.infrastructure;

import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.respository.CouponHistoryRepository;
import kr.hhplus.be.server.infrastructure.repository.CouponHistoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CouponHistoryRepositoryImpl implements CouponHistoryRepository {
    private final CouponHistoryJpaRepository couponHistoryJpaRepository;

    public CouponHistoryRepositoryImpl(CouponHistoryJpaRepository couponHistoryJpaRepository) {
        this.couponHistoryJpaRepository = couponHistoryJpaRepository;
    }

    @Override
    public List<CouponHistory> findByUserId(Long userId) {
        return couponHistoryJpaRepository.findByUserId(userId);
    }

    @Override
    public CouponHistory findByHisotryIdWithLock(Long historyId) {
        return couponHistoryJpaRepository.findByHistoryIdWithLock(historyId);
    }

    @Override
    public CouponHistory save(CouponHistory history) {
        return couponHistoryJpaRepository.save(history);
    }

    @Override
    public CouponHistory findByCouponIdAndUserId(Long couponId, Long userId) {
        return couponHistoryJpaRepository.findByCouponIdAndUserId(couponId, userId);
    }
}
