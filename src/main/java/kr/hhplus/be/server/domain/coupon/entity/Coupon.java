package kr.hhplus.be.server.domain.coupon.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.exception.CouponErrorCode;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
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

    @Column(name = "max_issue_count")
    private int maxIssueCount;

    @Column(name = "discount_rate")
    private int discountRate;

    @OneToMany
    @JoinColumn(name = "coupon_id")
    private List<CouponHistory> couponHistory = new ArrayList<>();

    public static Coupon create(String couponName, int maxIssueCount, int discountRate) {
        return Coupon.builder()
                .couponName(couponName)
                .maxIssueCount(maxIssueCount)
                .discountRate(discountRate)
                .build();
    }

    public long getCouponQuantity() {
        return this.maxIssueCount -
                this.couponHistory.stream().filter(coupon ->
                        coupon.getType().equals(HistoryType.ISSUE)).count();
    }

    public boolean isEmpty() {
        return this.maxIssueCount ==
                this.couponHistory.stream().filter(coupon ->
                        coupon.getType().equals(HistoryType.ISSUE)).count();
    }

    public void issueCoupon(CouponHistory couponHistory) throws CustomException {
        if (isEmpty()) {
            throw new CustomException(CouponErrorCode.EMPTY_ISSUE_COUPON);
        }
        this.couponHistory.add(couponHistory);
    }


}
