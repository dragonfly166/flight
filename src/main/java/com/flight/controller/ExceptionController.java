package com.flight.controller;

import com.flight.result.ApiResult;
import com.flight.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author sunlongfei
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {

    /**
     * 捕捉异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> globalException(Exception e) {
        log.info(e.getMessage());
        return ApiResult.fail(ResultCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
