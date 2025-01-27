package kr.hhplus.be.server.application.user;

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

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceConcurrencyTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceConcurrencyTest.class);
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void 사용자_1000원잔액충전_10번요청() throws InterruptedException {
        User user = userRepository.save(User.create("test"));
        Long amount = 1000L;
        int tryCount = 10;

        ExecutorService executorService = Executors.newFixedThreadPool(tryCount);
        CountDownLatch latch = new CountDownLatch(tryCount);
        for (int i = 0; i < tryCount; i++) {
            executorService.submit(() -> {
                userService.chargeUserAsset(user.getId(), amount);
                latch.countDown();
            });
        }
        executorService.shutdown();
        latch.await();

        Long expectedAssets = amount * tryCount;
        assertEquals(expectedAssets, userService.getAssetByUserId(user.getId()).getAmount());
    }

}