package kr.hhplus.be.server.infrastructure.repository;

import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponHistoryJpaRepository extends JpaRepository<CouponHistory, Long> {

    List<CouponHistory> findByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ch FROM CouponHistory ch where ch.id = :historyId")
    CouponHistory findByHistoryIdWithLock(Long historyId);

    CouponHistory findByCouponIdAndUserId(Long couponId, Long userId);
}
