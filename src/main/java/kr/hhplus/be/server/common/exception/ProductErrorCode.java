package kr.hhplus.be.server.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {
    PRODUCT_IS_NULL("Product is not found."),
    STOCK_NOT_ENOUGH("Product not enough stocks.");

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
