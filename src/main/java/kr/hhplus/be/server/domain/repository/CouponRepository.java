package kr.hhplus.be.server.domain.repository;

import kr.hhplus.be.server.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
