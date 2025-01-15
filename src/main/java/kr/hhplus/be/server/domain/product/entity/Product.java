package kr.hhplus.be.server.domain.product.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.common.exception.ProductErrorCode;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@Table(name = "product")
@ToString
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

    public void decreaseQuantity(Long quantity) throws CustomException {
        if (this.quantity < quantity) {
            throw new CustomException(ProductErrorCode.STOCK_NOT_ENOUGH);
        }
        this.quantity -= quantity;
    }
}
