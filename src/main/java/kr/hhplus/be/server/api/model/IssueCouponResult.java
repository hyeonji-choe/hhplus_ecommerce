package kr.hhplus.be.server.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.coupon.HistoryType;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.coupon.entity.CouponHistory;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * RegistCouponResult
 */
@Getter
@Builder
public class IssueCouponResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("history_id")
    private Long historyId;

    @JsonProperty("coupon_id")
    private Long couponId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("coupon_name")
    private String couponName;

    @JsonProperty("history_type")
    private HistoryType historyType;

    @JsonProperty("result")
    private String result;

    public static IssueCouponResult create(Long historyId, Long couponId, Long userId, String couponName, String result) {
        return IssueCouponResult.builder()
                .historyId(historyId)
                .couponId(couponId)
                .userId(userId)
                .couponName(couponName)
                .result(result)
                .build();
    }

    public static IssueCouponResult toResult(Coupon coupon, CouponHistory history) {
        return IssueCouponResult.builder()
                .historyId(history.getId())
                .couponId(history.getCouponId())
                .userId(history.getUserId())
                .couponName(coupon.getCouponName())
                .historyType(history.getType())
                .build();
    }
}
