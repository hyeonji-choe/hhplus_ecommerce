package kr.hhplus.be.server.domain.coupon.respository;

import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponRepository {
    Coupon findByCouponId(Long couponId);

    Page<Coupon> findAll(Pageable pageable);

    Coupon findByCouponIdWithLock(Long couponId);

    Coupon save(Coupon coupon);
}
