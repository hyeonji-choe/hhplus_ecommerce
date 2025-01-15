package kr.hhplus.be.server.domain.coupon.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.common.exception.CouponErrorCode;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.user.entity.User;
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
public class CouponHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_history_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "type")
    private HistoryType type;

    @Column(name = "issue_use_date")
    private LocalDateTime issueUseDate;

    @Column(name = "order_id")
    private Long orderId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Builder
    public CouponHistory(HistoryType type, LocalDateTime issueUseDate, User user, Coupon coupon) {
        this.type = type;
        this.issueUseDate = issueUseDate;
        this.user = user;
        this.coupon = coupon;
    }

    public void checkCoupon() throws CustomException {
        if (this.type.equals(HistoryType.USE)) {
            throw new CustomException(CouponErrorCode.ALREADY_USED_COUPON);
        }
    }

}
