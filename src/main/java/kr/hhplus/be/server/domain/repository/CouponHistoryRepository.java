package kr.hhplus.be.server.domain.repository;

import kr.hhplus.be.server.domain.entity.CouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponHistoryRepository extends JpaRepository<CouponHistory, Long> {
}
