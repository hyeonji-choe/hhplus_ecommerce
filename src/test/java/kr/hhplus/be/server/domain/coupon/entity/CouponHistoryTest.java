package kr.hhplus.be.server.domain.coupon.entity;

import kr.hhplus.be.server.common.exception.CouponErrorCode;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.user.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CouponHistoryTest {

    @Test
    public void 사용한_쿠폰_상태_확인() {
        long userId = 1L;
        long assetAmount = 0;
        String userName = "testUser";
        User user = new User(userId, userName, assetAmount);

        Long couponId = 1L;
        String name = "Test Coupon";
        int quantity = 5;
        int discountRate = 10;
        Coupon coupon = new Coupon(couponId, name, quantity, quantity, discountRate);

        CouponHistory history = new CouponHistory(1L, HistoryType.USE, LocalDateTime.now(), coupon.getId(), user.getId());

        assertThatThrownBy(history::checkCoupon)
                .isInstanceOf(CustomException.class)
                .hasMessage(CouponErrorCode.ALREADY_USED_COUPON.getMessage());
    }
}