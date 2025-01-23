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

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "coupon_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_history_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "type")
    private HistoryType type;

    @Column(name = "issue_use_date")
    private LocalDateTime issueUseDate;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "user_id")
    private Long userId;

    public static CouponHistory create(HistoryType type, LocalDateTime issueUseDate, Long userId, Long couponId) {
        return CouponHistory.builder()
                .type(type)
                .issueUseDate(issueUseDate)
                .userId(userId)
                .couponId(couponId).build();
    }

    public void checkCoupon() throws CustomException {
        if (this.type.equals(HistoryType.USE)) {
            throw new CustomException(CouponErrorCode.ALREADY_USED_COUPON);
        }
    }

}
