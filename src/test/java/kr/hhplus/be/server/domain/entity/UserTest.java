package kr.hhplus.be.server.domain.entity;

import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import kr.hhplus.be.server.domain.user.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void 유저_생성_테스트() {
        Long userId = 1L;
        String name = "USER";
        Long assetAmount = 0L;
        List<CouponHistory> historyList = new ArrayList<>();
        User user = new User(userId, name, assetAmount, historyList);

        assertThat(user).isNotNull()
                .extracting("name", "assetAmount")
                .containsExactly(name, assetAmount);

    }
}