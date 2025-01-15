package kr.hhplus.be.server.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * CouponInfo
 */
@Getter
@Builder
public class CouponResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("coupon_name")
    private String couponName;

    @JsonProperty("benefit_info")
    private int benefitInfo;

    @JsonProperty("quantity")
    private Long quantity;

    public CouponResult id(Long id) {
        this.id = id;
        return this;
    }

    public static CouponResult toResult(Coupon coupon) {
        return CouponResult.builder()
                .id(coupon.getId())
                .couponName(coupon.getCouponName())
                .benefitInfo(coupon.getDiscountRate())
                .quantity(coupon.getCouponQuantity())
                .build();
    }
}