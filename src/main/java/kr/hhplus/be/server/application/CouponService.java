package kr.hhplus.be.server.application;

public interface CouponService {
    void registerCoupon(String couponName, int quantity, int discountRate);

    void getCoupon(Long couponId, Long userId);
}
