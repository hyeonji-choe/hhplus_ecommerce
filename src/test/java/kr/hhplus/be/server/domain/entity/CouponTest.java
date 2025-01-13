package kr.hhplus.be.server.domain.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CouponTest {

    @Test
    public void testIsEmpty_NoHistoryEntries_ReturnsFalse() {
        // Arrange
        Coupon coupon = new Coupon("Test Coupon", 5, 10);
        coupon.couponHistory = new ArrayList<>();

        // Act
        boolean result = coupon.isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsEmpty_HistoryEntriesLessThanQuantity_ReturnsFalse() {
        // Arrange
        Coupon coupon = new Coupon("Test Coupon", 5, 10);

        List<CouponHistory> historyList = new ArrayList<>();
        historyList.add(new CouponHistory(HistoryType.ISSUE));
        historyList.add(new CouponHistory(HistoryType.ISSUE));

        coupon.couponHistory = historyList;

        // Act
        boolean result = coupon.isEmpty();

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsEmpty_HistoryEntriesEqualToQuantity_ReturnsTrue() {
        // Arrange
        Coupon coupon = new Coupon("Test Coupon", 2, 10);

        List<CouponHistory> historyList = new ArrayList<>();
        historyList.add(new CouponHistory(HistoryType.ISSUE));
        historyList.add(new CouponHistory(HistoryType.ISSUE));

        coupon.couponHistory = historyList;

        // Act
        boolean result = coupon.isEmpty();

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsEmpty_HistoryHasNonIssueEntries_ReturnsFalse() {
        // Arrange
        Coupon coupon = new Coupon("Test Coupon", 2, 10);

        List<CouponHistory> historyList = new ArrayList<>();
        historyList.add(new CouponHistory(HistoryType.USE));
        historyList.add(new CouponHistory(HistoryType.ISSUE));

        coupon.couponHistory = historyList;

        // Act
        boolean result = coupon.isEmpty();

        // Assert
        assertFalse(result);
    }

    private static class CouponHistory {
        private final HistoryType type;

        public CouponHistory(HistoryType type) {
            this.type = type;
        }

        public HistoryType getType() {
            return type;
        }
    }
}