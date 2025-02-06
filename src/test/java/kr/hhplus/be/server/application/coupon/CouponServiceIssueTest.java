package kr.hhplus.be.server.application.coupon;

import kr.hhplus.be.server.api.model.IssueCouponResult;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.coupon.respository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.coupon.respository.CouponRepository;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CouponServiceIssueTest {

    private static final Logger log = LoggerFactory.getLogger(CouponServiceIssueTest.class);
    @Mock
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponHistoryRepository couponHistoryRepository;

    @Mock
    private RedisTemplate<String, String> redisTemplate;

    @InjectMocks
    private CouponServiceImpl couponService;

    @BeforeEach
    void setup() {
        String COUPON_STOCK_KEY = "coupon_stock:1";
        String COUPON_ISSUED_KEY = "coupon_issued:1";
    }

    @Test
    public void shouldIssueCouponSuccessfully() throws CustomException {
        when(redisTemplate.opsForSet().isMember(anyString(), anyString())).thenReturn(false);
        when(redisTemplate.opsForValue().decrement(anyString())).thenReturn(5L); // Simulate stock left

        User user = new User(1L, "testUser", 0);
        Coupon coupon = new Coupon(1L, "Discount Coupon", 10, 10, 10);

        when(userRepository.findByUserId(1L)).thenReturn(user);
        when(couponRepository.findByCouponId(1L)).thenReturn(coupon);

        IssueCouponResult result = couponService.issueCoupon(1L, 1L);

        Assertions.assertNotNull(result);
        assertEquals("success", result.getResult());

        verify(redisTemplate).opsForSet();
        verify(redisTemplate.opsForSet()).add(anyString(), anyString());
        verify(redisTemplate.opsForValue()).decrement(anyString());
        verify(couponHistoryRepository).save(any(CouponHistory.class));
    }

    @Test
    public void shouldFailWhenCouponOutOfStock() {
        when(redisTemplate.opsForValue().decrement(anyString())).thenReturn(-1L);
        assertThrows(CustomException.class, () -> couponService.issueCoupon(1L, 1L));
        verify(redisTemplate.opsForValue()).increment(anyString());
    }

    @Test
    public void shouldFailIfUserAlreadyIssuedCoupon() {
        when(redisTemplate.opsForSet().isMember(anyString(), anyString())).thenReturn(true);

        assertThrows(CustomException.class, () -> couponService.issueCoupon(1L, 1L));
    }
}
