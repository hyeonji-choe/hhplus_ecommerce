package kr.hhplus.be.server.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "coupon")
@ToString
@NoArgsConstructor
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "discount_rate")
    private int discountRate;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @JoinColumn(name = "coupon_id")
    private List<CouponHistory> couponHistory = new ArrayList<>();

    public Coupon(String couponName, int quantity, int discountRate) {
        this.couponName = couponName;
        this.quantity = quantity;
        this.discountRate = discountRate;
    }

    public boolean isEmpty() {
        return this.quantity ==
                this.couponHistory.stream().filter(coupon ->
                        coupon.getType().equals(HistoryType.ISSUE)).count();
    }

    public void getCoupon(CouponHistory couponHistory) {
        if (isEmpty()) {
            throw new IllegalStateException("coupon is empty");
        }
        this.couponHistory.add(couponHistory);
    }


}
