package com.demo.boot.api.exception;

import com.demo.boot.api.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ApiCommonException extends Exception{

    private String message;

    private String code;

    private Throwable cause;

    private List<String> args;

    private ErrorCodeEnum errorCodeEnum;

    public ApiCommonException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum, errorCodeEnum.getCode(), errorCodeEnum.getMessage());
    }

    public ApiCommonException(ErrorCodeEnum errorCodeEnum, String code, String message) {
        this.errorCodeEnum = errorCodeEnum;
        this.code = code;
        this.message = message;
    }
}
