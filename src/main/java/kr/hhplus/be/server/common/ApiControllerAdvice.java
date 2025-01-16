package kr.hhplus.be.server.common;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.ApiResponse;
import kr.hhplus.be.server.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Void> handleIllegalArgumentException(final IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.badRequest(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<Void> handleEntityNotFoundException(final EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.badRequest(e.getMessage());
    }

    //Custom Exception
    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> handleCustomException(final CustomException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.badRequest(e.getMessage());
    }


}