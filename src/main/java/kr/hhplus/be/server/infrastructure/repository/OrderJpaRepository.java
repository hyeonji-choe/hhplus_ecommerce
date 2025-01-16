package kr.hhplus.be.server.infrastructure.repository;

import kr.hhplus.be.server.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Order findByOrderId(Long orderId);

    Order save(Long orderId);
}
