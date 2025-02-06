package kr.hhplus.be.server.infrastructure;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.hhplus.be.server.api.model.TopOrderProduct;
import kr.hhplus.be.server.domain.order.entity.OrderItem;
import kr.hhplus.be.server.domain.order.entity.QOrderItem;
import kr.hhplus.be.server.domain.order.repository.OrderItemRepository;
import kr.hhplus.be.server.infrastructure.repository.OrderItemJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final OrderItemJpaRepository orderItemJpaRepository;
    private final JPAQueryFactory queryFactory;

    public OrderItemRepositoryImpl(OrderItemJpaRepository orderItemJpaRepository, JPAQueryFactory queryFactory) {
        this.orderItemJpaRepository = orderItemJpaRepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemJpaRepository.findByOrderId(orderId);
    }

    @Override
    public OrderItem save(OrderItem product) {
        return orderItemJpaRepository.save(product);
    }

    @Override
    public List<TopOrderProduct> findTop5OrderProducts() {
        QOrderItem orderItem = QOrderItem.orderItem;
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);

        return queryFactory.select(Projections.constructor(
                        TopOrderProduct.class,
                        orderItem.productId.as("productId"),
                        orderItem.quantity.sum().as("totalQuantity")
                ))
                .from(orderItem)
                .where(orderItem.createdAt.after(threeDaysAgo))
                .groupBy(orderItem.productId)
                .orderBy(orderItem.quantity.sum().desc())
                .limit(5)
                .fetch();
    }
}
