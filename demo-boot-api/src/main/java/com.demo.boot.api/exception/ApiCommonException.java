package com.demo.boot.api.exception;

import com.demo.boot.api.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ApiCommonException extends RuntimeException{

    private String message;

    private String code;

    public ApiCommonException(Throwable cause) {
        super(cause);
    }

    public ApiCommonException(String message) {
        super(message);
        this.message = message;
    }

    public ApiCommonException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiCommonException(ErrorCodeEnum errorCodeEnum) {
        this(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
    }

    public ApiCommonException(ErrorCodeEnum errorCodeEnum, String args) {
        this(errorCodeEnum.getMessage() + ": " + args);
        this.code = errorCodeEnum.getCode();
    }
}
