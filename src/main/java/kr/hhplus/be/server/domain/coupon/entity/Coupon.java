package kr.hhplus.be.server.domain.coupon.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "coupon")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "max_issue_count")
    private int maxIssueCount;

    @Column(name = "discount_rate")
    private int discountRate;

    public static Coupon create(String couponName, int maxIssueCount, int discountRate) {
        return Coupon.builder()
                .couponName(couponName)
                .quantity(maxIssueCount)
                .maxIssueCount(maxIssueCount)
                .discountRate(discountRate)
                .build();
    }

    public void decreaseQuantity() {
        this.quantity = this.quantity - 1;
    }

}
