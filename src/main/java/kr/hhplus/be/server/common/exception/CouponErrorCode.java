package kr.hhplus.be.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponErrorCode implements ErrorCode {
    EMPTY_ISSUE_COUPON("Coupon is empty."),
    ALREADY_USED_COUPON("The Coupon is already used."),
    ALREADY_ISSUED("The Coupon is already issued.");

    private final String message;

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
