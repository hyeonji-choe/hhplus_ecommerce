package kr.hhplus.be.server.application.coupon;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.CouponResult;
import kr.hhplus.be.server.api.model.IssueCouponResult;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.respository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.coupon.respository.CouponRepository;
import kr.hhplus.be.server.domain.user.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponHistoryRepository couponHistoryRepository;

    @InjectMocks
    private CouponService couponService;

    @Test
    public void getCouponInfoByCouponId() {
        // given
        Long couponId = 1L;
        List<CouponHistory> historyList = new ArrayList<>();
        Coupon mockCoupon = new Coupon(couponId, "TEST쿠폰", 1000, 10, historyList);

        when(couponRepository.findByCouponId(couponId)).thenReturn(mockCoupon);

        // when
        CouponResult result = couponService.getCouponInfoByCouponId(couponId);

        // then
        assertNotNull(result);
        assertEquals(couponId, result.getId());
        assertEquals("TEST쿠폰", result.getCouponName());
        verify(couponRepository, times(1)).findByCouponId(couponId);
    }

    @Test
    public void getNullCouponInfoByCouponId() {
        // given
        Long couponId = 1L;

        when(couponRepository.findByCouponId(couponId)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> couponService.getCouponInfoByCouponId(couponId))
                .isInstanceOf(EntityNotFoundException.class);
        verify(couponRepository, times(1)).findByCouponId(couponId);
        verify(couponHistoryRepository, never()).save(any(CouponHistory.class));
    }

    @Test
    public void issueCoupon() throws CustomException {
        // given
        Long userId = 1L;
        Long couponId = 1L;
        List<CouponHistory> historyList = new ArrayList<>();

        User user = new User(userId, "test", 0, historyList);
        Coupon mockCoupon = new Coupon(couponId, "TEST쿠폰", 1000, 10, historyList);
        CouponHistory mockIssuance = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), null, user, mockCoupon);

        when(couponRepository.findByCouponIdWithLock(couponId)).thenReturn(mockCoupon);
        when(couponHistoryRepository.save(any(CouponHistory.class))).thenReturn(mockIssuance);

        // when
        IssueCouponResult result = couponService.issueCoupon(userId, couponId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(couponId, result.getCouponId());
        verify(couponRepository, times(1)).findByCouponIdWithLock(couponId);
        verify(couponHistoryRepository, times(1)).save(any(CouponHistory.class));
    }

    @Test
    public void issueNotFoundCoupon() {
        // given
        Long userId = 1L;
        Long couponId = 1L;

        // when
        when(couponRepository.findByCouponIdWithLock(couponId)).thenReturn(null);

        // then
        assertThatThrownBy(() -> couponService.issueCoupon(userId, couponId))
                .isInstanceOf(EntityNotFoundException.class);
        verify(couponRepository, times(1)).findByCouponIdWithLock(couponId);
        verify(couponHistoryRepository, never()).save(any(CouponHistory.class));
    }

    @Test
    public void userIssuedCouponList() {
        // given
        Long userId = 1L;
        Long couponId = 1L;
        List<CouponHistory> historyList = new ArrayList<>();

        User user = new User(userId, "test", 0, historyList);
        Coupon mockCoupon = new Coupon(1L, "TEST쿠폰1", 1000, 10, historyList);
        Coupon mockCoupon2 = new Coupon(2L, "TEST쿠폰2", 1000, 10, historyList);
        CouponHistory history1 = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), null, user, mockCoupon);
        CouponHistory history2 = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), null, user, mockCoupon2);

        when(couponHistoryRepository.findByUserId(userId)).thenReturn(Arrays.asList(history1, history2));
        when(couponRepository.findByCouponId(1L)).thenReturn(mockCoupon);
        when(couponRepository.findByCouponId(2L)).thenReturn(mockCoupon2);

        // when
        List<IssueCouponResult> results = couponService.userIssuedCoupons(userId);

        // then
        assertNotNull(results);
        assertEquals(2, results.size());
        verify(couponHistoryRepository, times(1)).findByUserId(userId);
        verify(couponRepository, times(1)).findByCouponId(1L);
        verify(couponRepository, times(1)).findByCouponId(2L);
    }

    @Test
    public void useUserIssuedCoupon() {
        // Given
        Long userId = 1L;
        Long couponId = 100L;
        List<CouponHistory> historyList = new ArrayList<>();

        User user = new User(userId, "test", 0, historyList);

        Coupon mockCoupon = new Coupon(1L, "TEST쿠폰1", 1000, 10, historyList);
        CouponHistory history1 = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), null, user, mockCoupon);

        when(couponRepository.findByCouponIdWithLock(couponId)).thenReturn(mockCoupon);
        when(couponHistoryRepository.findByCouponIdAndUserId(couponId, userId)).thenReturn(history1);

        CouponHistory savedIssue = new CouponHistory(1L, HistoryType.USE, LocalDateTime.now(), null, user, mockCoupon);
        when(couponHistoryRepository.save(any(CouponHistory.class))).thenReturn(savedIssue);

        // When
        IssueCouponResult result = couponService.useUserIssuedCoupon(userId, couponId);

        // Then
        assertNotNull(result);
        assertEquals(couponId, result.getCouponId());
        assertEquals(userId, result.getUserId());
        assertEquals(HistoryType.USE, result.getHistoryType());

        verify(couponRepository, times(1)).findByCouponIdWithLock(couponId);
        verify(couponHistoryRepository, times(1)).findByCouponIdAndUserId(couponId, userId);
        verify(couponHistoryRepository, times(1)).save(any(CouponHistory.class));
    }

    @Test
    public void testUseUserIssuedCoupon_CouponNotFound() {
        // Given
        Long userId = 1L;
        Long couponId = 100L;

        when(couponRepository.findByCouponIdWithLock(couponId)).thenReturn(null);

        // When & Then
        assertThatThrownBy(() -> couponService.useUserIssuedCoupon(userId, couponId))
                .isInstanceOf(EntityNotFoundException.class);

        verify(couponRepository, times(1)).findByCouponIdWithLock(couponId);
        verify(couponHistoryRepository, never()).findByHisotryIdWithLock(anyLong());
        verify(couponHistoryRepository, never()).save(any(CouponHistory.class));
    }

    @Test
    public void testUseUserIssuedCoupon_IssuanceNotFound() {
        // Given
        Long userId = 1L;
        Long couponId = 100L;
        List<CouponHistory> historyList = new ArrayList<>();
        Coupon mockCoupon = new Coupon(1L, "TEST쿠폰1", 1000, 10, historyList);

        when(couponRepository.findByCouponIdWithLock(couponId)).thenReturn(mockCoupon);
        when(couponHistoryRepository.findByCouponIdAndUserId(couponId, userId)).thenReturn(null);

        // When & Then
        assertThatThrownBy(() -> couponService.useUserIssuedCoupon(userId, couponId))
                .isInstanceOf(EntityNotFoundException.class);

        verify(couponRepository, times(1)).findByCouponIdWithLock(couponId);
        verify(couponHistoryRepository, times(1)).findByCouponIdAndUserId(couponId, userId);
        verify(couponHistoryRepository, never()).save(any(CouponHistory.class));
    }
}