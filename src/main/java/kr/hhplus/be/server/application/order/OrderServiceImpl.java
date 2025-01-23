package kr.hhplus.be.server.application.order;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.OrderResult;
import kr.hhplus.be.server.domain.order.entity.Order;
import kr.hhplus.be.server.domain.order.entity.OrderItem;
import kr.hhplus.be.server.domain.order.repository.OrderItemRepository;
import kr.hhplus.be.server.domain.order.repository.OrderRepository;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public OrderResult findOrderInfo(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) throw new EntityNotFoundException("Order not found.");

        List<OrderItem> productList = orderItemRepository.findByOrderId(orderId);

        List<OrderItemDto> items = productList.stream()
                .map(OrderItemDto::toDto).toList();

        OrderResult result = OrderResult.toResult(order);
        result.setOrderItemList(items);
        return result;
    }

    @Transactional
    public OrderResult createOrder(OrderServiceRequest request) {
        User user = userRepository.findByUserId(request.getUserId());
        if (ObjectUtils.isEmpty(user)) throw new EntityNotFoundException("User not found.");

        Order order = orderRepository.save(Order.builder()
                .userId(user.getId())
                .totalPrice(request.getTotalPrice())
                .build());
        return OrderResult.toResult(order);
    }

    @Transactional
    public List<OrderItem> createOrderProduct(Long orderId, List<OrderProductServiceRequest> requests) {
        List<OrderItem> result = new ArrayList<>();

        Order order = orderRepository.findByOrderId(orderId);
        if (order == null) throw new EntityNotFoundException("Order not found.");

        for (OrderProductServiceRequest request : requests) {
            Product product = productRepository.findByProductIdWithLock(request.getProductId());
            OrderItem orderItem = OrderItem.create(product, request.getQuantity(), order);

            OrderItem item = orderItemRepository.save(orderItem);
            result.add(item);
        }
        return result;
    }

}
