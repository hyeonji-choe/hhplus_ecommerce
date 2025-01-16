package kr.hhplus.be.server.application.coupon;

import kr.hhplus.be.server.common.exception.CustomException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CouponServiceConcurrencyTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponHistoryRepository couponHistoryRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    public void 쿠폰_최대수량_30개_40명_동시발급_테스트() throws InterruptedException {
        //given
        int maxIssueCount = 30;
        int tryIssueCount = 40;
        List<CouponHistory> historyList = new ArrayList<>();
        Coupon coupon = couponRepository.save(
                new Coupon(1L, "TEST쿠폰", maxIssueCount, 10, historyList));

        ExecutorService executorService = Executors.newFixedThreadPool(tryIssueCount);
        CountDownLatch latch = new CountDownLatch(tryIssueCount);

        AtomicInteger exceptionCount = new AtomicInteger(0);

        for (int i = 1; i <= tryIssueCount; i++) {
            User user = userRepository.save(User.create("testUser" + i));

            executorService.submit(() -> {
                try {
                    couponService.issueCoupon(coupon.getId(), user.getId());
                } catch (CustomException e) {
                    exceptionCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();

        assertThat(exceptionCount.get()).isEqualTo(tryIssueCount - maxIssueCount);
    }
}
