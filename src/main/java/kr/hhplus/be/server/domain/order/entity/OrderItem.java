package kr.hhplus.be.server.domain.order.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", insertable = false, nullable = false)
    private Long id;
    private Long productId;
    private int quantity;
    private Long orderId;

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public OrderItem create(Product product, int quantity, Order order) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
        if (product == null) {
            throw new IllegalArgumentException("product must not be null");
        }
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("quantity must be less than product quantity");
        }
        return OrderItem.builder()
                .productId(product.getId())
                .product(product)
                .quantity(quantity)
                .order(order)
                .orderId(order.getId())
                .build();
    }
}
