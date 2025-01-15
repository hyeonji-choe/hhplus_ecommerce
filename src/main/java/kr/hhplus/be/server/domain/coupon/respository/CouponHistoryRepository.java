package kr.hhplus.be.server.domain.coupon.respository;

import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;

import java.util.List;

public interface CouponHistoryRepository {

    List<CouponHistory> findByUserId(Long userId);

    CouponHistory findByHisotryIdWithLock(Long historyId);

    CouponHistory save(CouponHistory history);

    CouponHistory findByCouponIdAndUserId(Long couponId, Long userId);
}
