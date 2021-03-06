package com.flight.result;

import lombok.Data;

/**
 * ιη¨θΏε
 * @author sunlongfei
 */
@Data
public class ApiResult<T> {

    private String code;

    private String msg;

    private T data;

    public ApiResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <W> ApiResult<W> success(W data) {
        return new ApiResult<>(ResultCode.SUCCESS, "success", data);
    }

    public static ApiResult<?> success() {
        return new ApiResult<>(ResultCode.SUCCESS, "success", null);
    }

    public static ApiResult<?> fail(String code, String msg) {
        return new ApiResult<>(code, msg, null);
    }
}
