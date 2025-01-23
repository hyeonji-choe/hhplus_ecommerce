package kr.hhplus.be.server.domain.coupon;

import kr.hhplus.be.server.common.exception.CouponErrorCode;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.coupon.entity.Coupon;
import kr.hhplus.be.server.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CouponIssuer {
    public Coupon issue(User user, Coupon coupon) throws CustomException {
        if (coupon.getQuantity() <= 0) {
            throw new CustomException(CouponErrorCode.EMPTY_ISSUE_COUPON);
        }
        coupon.decreaseQuantity();
        return coupon;
    }
}
