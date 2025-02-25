package kr.hhplus.be.server.application.coupon;

import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.respository.CouponHistoryRepository;
import kr.hhplus.be.server.domain.coupon.respository.CouponRepository;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CouponServiceConcurrencyTest {

    private static final Logger log = LoggerFactory.getLogger(CouponServiceConcurrencyTest.class);
    @Mock
    private UserRepository userRepository;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponHistoryRepository couponHistoryRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    @Test
    public void 비관락조회_쿠폰_최대수량_30개_40명_동시발급_테스트() throws InterruptedException {
        //given
        int maxIssueCount = 30;
        int tryIssueCount = 40;
        Coupon coupon = couponRepository.save(
                new Coupon(1L, "TEST쿠폰", maxIssueCount, maxIssueCount, 10));

        ExecutorService executorService = Executors.newFixedThreadPool(tryIssueCount);
        CountDownLatch latch = new CountDownLatch(tryIssueCount);

        AtomicInteger exceptionCount = new AtomicInteger(0);

        for (int i = 1; i <= tryIssueCount; i++) {
            User user = userRepository.save(User.create("testUser" + i));

            executorService.submit(() -> {
                try {
                    couponService.issueCoupon(coupon.getId(), user.getId());
                    log.debug("issueCoupon");
                } catch (CustomException e) {
                    log.debug("failCoupon");
                    exceptionCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        executorService.shutdown();
        latch.await();

        assertThat(exceptionCount.get()).isEqualTo(tryIssueCount - maxIssueCount);
    }

    @Test
    public void 낙관락조회_쿠폰_최대수량_30개_40명_동시발급_테스트() throws InterruptedException {
        //given
        int maxIssueCount = 30;
        int tryIssueCount = 40;
        Coupon coupon = couponRepository.save(
                new Coupon(1L, "TEST쿠폰", maxIssueCount, maxIssueCount, 10));

        ExecutorService executorService = Executors.newFixedThreadPool(tryIssueCount);
        CountDownLatch latch = new CountDownLatch(tryIssueCount);

        AtomicInteger exceptionCount = new AtomicInteger(0);

        for (int i = 1; i <= tryIssueCount; i++) {
            User user = userRepository.save(User.create("testUser" + i));

            executorService.submit(() -> {
                try {
                    couponService.issueCouponWithOptimisticLock(coupon.getId(), user.getId());
                    log.debug("issueCoupon");
                } catch (CustomException e) {
                    log.debug("failCoupon");
                    exceptionCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        executorService.shutdown();
        latch.await();

        assertThat(exceptionCount.get()).isEqualTo(tryIssueCount - maxIssueCount);
    }
}
