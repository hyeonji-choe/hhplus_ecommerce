package kr.hhplus.be.server.domain.coupon.entity;

import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.user.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CouponTest {

    @Test
    public void 쿠폰_객체_생성_테스트() {
        // Arrange
        Long couponId = 1L;
        String name = "Test Coupon";
        int quantity = 5;
        int discountRate = 10;
        List<CouponHistory> historyList = new ArrayList<>();

        Coupon coupon = new Coupon(couponId, name, quantity, discountRate, historyList);

        // Assert
        assertThat(coupon).isNotNull()
                .extracting("name", "quantity", "discountRate")
                .containsExactly(name, quantity, discountRate);
    }

    @Test
    public void 쿠폰_발급_성공시_수량1_감소() {
        // Arrange
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

        int expectedQuantity = quantity - 1;
        coupon.issueCoupon(new CouponHistory(HistoryType.ISSUE, LocalDateTime.now(), user, coupon));

        assertThat(coupon.getCouponQuantity()).isEqualTo(expectedQuantity);
    }

}