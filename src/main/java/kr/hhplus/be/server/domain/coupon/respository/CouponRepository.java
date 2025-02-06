package kr.hhplus.be.server.domain.coupon.respository;

import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponRepository {
    Coupon findByCouponId(Long couponId);

    List<Coupon> findAllList();

    Page<Coupon> findAll(Pageable pageable);

    Coupon findByCouponIdWithLock(Long couponId);

    Coupon findByCouponIdWithOptimisticLock(Long couponId);

    Coupon save(Coupon coupon);
}
