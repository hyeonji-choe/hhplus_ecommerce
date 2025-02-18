package kr.hhplus.be.server.domain.coupon;

import jakarta.annotation.PostConstruct;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.respository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponInitializer {
    private final RedisTemplate<String, String> redisTemplate;
    private final CouponRepository couponRepository;

    private static final String COUPON_STOCK_KEY = "coupon_stock:";

    @PostConstruct
    public void initializeCoupons() {
        List<Coupon> coupons = couponRepository.findAllList();
        for (Coupon coupon : coupons) {
            redisTemplate.opsForValue().set(COUPON_STOCK_KEY + coupon.getId(), String.valueOf(coupon.getQuantity()));
        }
    }
}
