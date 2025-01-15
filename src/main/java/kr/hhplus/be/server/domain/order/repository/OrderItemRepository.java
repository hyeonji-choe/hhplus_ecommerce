package kr.hhplus.be.server.domain.order.repository;

import kr.hhplus.be.server.api.model.TopOrderProduct;
import kr.hhplus.be.server.domain.order.entity.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    List<OrderItem> findByOrderId(Long orderId);

    OrderItem save(OrderItem product);

    List<TopOrderProduct> findTop5OrderProducts();
}
