package kr.hhplus.be.server.domain.repository;

import kr.hhplus.be.server.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
