package kr.hhplus.be.server.domain.coupon.respository;

import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;

import java.util.List;

public interface CouponHistoryRepository {

    List<CouponHistory> findByUserId(Long userId);

    List<CouponHistory> findByHisotryIdWithLock(Long couponId);

    CouponHistory save(CouponHistory history);

    List<CouponHistory> findByCouponIdAndUserId(Long couponId, Long userId);
}
