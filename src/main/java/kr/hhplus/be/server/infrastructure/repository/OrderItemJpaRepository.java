package kr.hhplus.be.server.infrastructure.repository;

import kr.hhplus.be.server.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderId(Long orderId);

    OrderItem save(OrderItem orderProduct);
}