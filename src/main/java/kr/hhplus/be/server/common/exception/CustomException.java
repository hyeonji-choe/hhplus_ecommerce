package kr.hhplus.be.server.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {
    private final ErrorCode errorCode;
    private final Object[] args;

    public CustomException(ErrorCode errorCode, Object... args) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.args = args;
    }
}
