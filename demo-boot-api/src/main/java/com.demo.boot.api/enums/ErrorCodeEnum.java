package com.demo.boot.api.enums;

import lombok.Getter;

import java.text.MessageFormat;
import java.util.List;

@Getter
public enum ErrorCodeEnum {

    REQUEST_PARAM_NULL_KEY("PD.PM.CS.100002", "必传参数为空");

    private String code;
    private String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorCodeEnum getErrorCodeByCode(String code) {
        ErrorCodeEnum[] enums = values();
        for (ErrorCodeEnum constant : enums) {
            if (constant.getCode().equals(code)) {
                return constant;
            }
        }
        return null;
    }

    // 占位符填充
    public static String getFillMessageByCode(ErrorCodeEnum errorCodeEnum, List<String> args) {
        if (errorCodeEnum == null) {
            return null;
        }
        // 不需要填充， 直接返回message
        if (args == null) {
            return errorCodeEnum.getMessage();
        }
        return MessageFormat.format(errorCodeEnum.getMessage(), args.toArray());
    }
}
