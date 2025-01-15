package kr.hhplus.be.server.application.order;

import kr.hhplus.be.server.api.model.OrderResult;
import kr.hhplus.be.server.api.model.TopOrderProduct;
import kr.hhplus.be.server.domain.order.entity.OrderItem;

import java.util.List;

public interface OrderService {
    OrderResult findOrderInfo(Long orderId);

    OrderResult createOrder(OrderServiceRequest request);

    List<OrderItem> createOrderProduct(Long orderId, List<OrderProductServiceRequest> requests);

    List<TopOrderProduct> findTop5OrderProducts();
}
