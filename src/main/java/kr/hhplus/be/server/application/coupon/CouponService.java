package kr.hhplus.be.server.application.coupon;

import kr.hhplus.be.server.api.model.CouponResult;
import kr.hhplus.be.server.api.model.IssueCouponResult;
import kr.hhplus.be.server.common.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CouponService {
    CouponResult registerCoupon(String couponName, int quantity, int discountRate);

    CouponResult getCouponInfoByCouponId(Long couponId);

    Page<CouponResult> getCouponList(Pageable pageable);

    IssueCouponResult issueCoupon(Long couponId, Long userId) throws CustomException;

    IssueCouponResult useUserIssuedCoupon(Long userId, Long couponId);

    List<IssueCouponResult> userIssuedCoupons(Long userId);
}
