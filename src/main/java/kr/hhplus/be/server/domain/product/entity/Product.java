package kr.hhplus.be.server.domain.product.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ProductErrorCode;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", insertable = false, nullable = false)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private int quantity;

    public static Product create(String productName, Double price, int quantity) {
        return Product.builder()
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public void decreaseQuantity(Long quantity) throws CustomException {
        if (this.quantity < quantity) {
            throw new CustomException(ProductErrorCode.STOCK_NOT_ENOUGH);
        }
        this.quantity -= quantity;
    }
}
