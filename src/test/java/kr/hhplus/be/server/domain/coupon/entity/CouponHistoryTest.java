package kr.hhplus.be.server.domain.coupon.entity;

import kr.hhplus.be.server.common.exception.CouponErrorCode;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.user.entity.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CouponHistoryTest {

    @Test
    public void 사용한_쿠폰_상태_확인() {
        long userId = 1L;
        long assetAmount = 0;
        String userName = "testUser";
        List<CouponHistory> historyList = new ArrayList<>();
        User user = new User(userId, userName, assetAmount, historyList);

        Long couponId = 1L;
        String name = "Test Coupon";
        int quantity = 5;
        int discountRate = 10;
        Coupon coupon = new Coupon(couponId, name, quantity, discountRate, historyList);

        CouponHistory history = new CouponHistory(HistoryType.USE, LocalDateTime.now(), user, coupon);

        assertThatThrownBy(history::checkCoupon)
//                .isInstanceOf(CustomException.class)
                .hasMessage(CouponErrorCode.ALREADY_USED_COUPON.getMessage());
    }
}