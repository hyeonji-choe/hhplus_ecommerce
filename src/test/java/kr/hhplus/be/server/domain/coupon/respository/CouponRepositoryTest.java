package kr.hhplus.be.server.domain.coupon.respository;

import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.infrastructure.CouponRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(CouponRepositoryImpl.class)
public class CouponRepositoryTest {

    @Autowired
    private CouponRepository repository;

    @Test
    public void 쿠폰_아이디로_쿠폰정보_조회() {
        Coupon coupon = Coupon.create("Test Coupon", 5, 10);
        Coupon savedCoupon = repository.save(coupon);

        Coupon result = repository.findByCouponId(savedCoupon.getId());

        assertNotNull(result);
        assertEquals(savedCoupon.getCouponName(), result.getCouponName());
        assertEquals(savedCoupon.getDiscountRate(), result.getDiscountRate());

    }

    @Test
    public void 쿠폰정보를_lock을_걸어_조회() {
// given
        Coupon coupon = Coupon.create("Test Coupon", 5, 10);

        Coupon savedCoupon = repository.save(coupon);

        // when
        Coupon result = repository.findByCouponIdWithLock(savedCoupon.getId());

        // then
        assertNotNull(result);
        assertEquals(savedCoupon.getCouponName(), result.getCouponName());
        assertEquals(savedCoupon.getDiscountRate(), result.getDiscountRate());
    }
}