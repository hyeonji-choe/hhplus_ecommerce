package kr.hhplus.be.server.domain.entity;

import jakarta.persistence.*;
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
    private long price;
    @Column(name = "quantity")
    private int quantity;
}
