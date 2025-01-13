package kr.hhplus.be.server.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "coupon_history")
@ToString
@NoArgsConstructor
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

}
