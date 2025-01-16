package kr.hhplus.be.server.infrastructure.repository;

import kr.hhplus.be.server.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi from OrderItem oi where oi.orderId = :orderId")
    List<OrderItem> findByOrderId(Long orderId);

    OrderItem save(OrderItem orderItem);
}
