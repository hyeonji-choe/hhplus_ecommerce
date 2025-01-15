package kr.hhplus.be.server.api.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private final int code;

    private final String message;

    private final T data;

    private ApiResponse(final int code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(final int code, final String message, final T data) {
        return new ApiResponse<>(code, message, data);
    }

    public static <T> ApiResponse<T> ok(final T data) {
        return of(HttpStatus.OK.value(), null, data);
    }

    public static <T> ApiResponse<T> badRequest(final String message) {
        return of(HttpStatus.BAD_REQUEST.value(), message, null);
    }
}
