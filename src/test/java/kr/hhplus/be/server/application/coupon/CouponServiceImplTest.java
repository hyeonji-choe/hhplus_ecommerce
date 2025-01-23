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
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
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
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponHistoryRepository couponHistoryRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    public void getCouponInfoByCouponId() {
        // given
        CouponResult result = couponService.registerCoupon("TEST쿠폰", 100, 10);
        Coupon mockCoupon = new Coupon(result.getId(), result.getCouponName(), result.getQuantity(), result.getQuantity(), result.getBenefitInfo());

        when(couponRepository.findByCouponId(result.getId())).thenReturn(mockCoupon);
        // when
        CouponResult couponResult = couponService.getCouponInfoByCouponId(result.getId());

        // then
        assertNotNull(couponResult);
        assertEquals(result.getId(), couponResult.getId());
        assertEquals("TEST쿠폰", couponResult.getCouponName());
        verify(couponRepository, times(1)).findByCouponId(result.getId());
    }

    @Test
    public void getNullCouponInfoByCouponId() {
        // given
        Long couponId = 1L;

        //when(couponRepository.findByCouponId(couponId)).thenReturn(null);

        // when & then
        assertThatThrownBy(() -> couponService.getCouponInfoByCouponId(couponId))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void issueCoupon() throws CustomException {
        // given
        Long userId = 1L;
        Long couponId = 1L;
        List<CouponHistory> historyList = new ArrayList<>();

        User user = new User(userId, "test", 0);
        Coupon mockCoupon = new Coupon(couponId, "TEST쿠폰", 1000, 1000, 10);
        CouponHistory mockIssuance = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), couponId, userId);

        when(userRepository.save(user)).thenReturn(user);
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

        User user = new User(userId, "test", 0);
        Coupon mockCoupon = new Coupon(1L, "TEST쿠폰1", 1000, 1000, 10);
        Coupon mockCoupon2 = new Coupon(2L, "TEST쿠폰2", 1000, 1000, 10);
        CouponHistory history1 = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), mockCoupon.getId(), userId);
        CouponHistory history2 = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), mockCoupon2.getId(), userId);

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
    public void useUserIssuedCoupon() throws CustomException {
        // Given
        Long userId = 1L;
        Long couponId = 100L;
        List<CouponHistory> historyList = new ArrayList<>();

        User user = new User(userId, "test", 0);

        Coupon mockCoupon = new Coupon(1L, "TEST쿠폰1", 1000, 1000, 10);
        CouponHistory history1 = new CouponHistory(1L, HistoryType.ISSUE, LocalDateTime.now(), mockCoupon.getId(), user.getId());

        when(couponRepository.findByCouponIdWithLock(couponId)).thenReturn(mockCoupon);
        when(couponHistoryRepository.findByCouponIdAndUserId(couponId, userId)).thenReturn(List.of(history1));

        CouponHistory savedIssue = new CouponHistory(1L, HistoryType.USE, LocalDateTime.now(), mockCoupon.getId(), user.getId());
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
        Coupon mockCoupon = new Coupon(1L, "TEST쿠폰1", 1000, 1000, 10);

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