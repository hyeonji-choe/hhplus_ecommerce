package kr.hhplus.be.server.domain.coupon.entity;

import kr.hhplus.be.server.common.exception.CustomException;
import org.junit.jupiter.api.Test;

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

        Coupon coupon = new Coupon(couponId, name, quantity, quantity, discountRate);

        // Assert
        assertThat(coupon).isNotNull()
                .extracting("couponName", "maxIssueCount", "discountRate")
                .containsExactly(name, quantity, discountRate);
    }

    @Test
    public void 쿠폰_발급_성공시_수량1_감소() throws CustomException {
        // Arrange
        Long couponId = 1L;
        String name = "Test Coupon";
        int quantity = 5;
        int expectedQuantity = quantity - 1;
        int discountRate = 10;
        Coupon coupon = new Coupon(couponId, name, quantity, quantity, discountRate);

        coupon.decreaseQuantity();

        assertThat(coupon.getQuantity()).isEqualTo(expectedQuantity);
    }

}