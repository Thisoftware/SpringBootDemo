package com.demo.boot.api.enums;

import com.demo.boot.api.exception.ApiCommonException;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author wyl
 * @since 2023/02/06 17:47:00
 */
@Getter
public enum LimitTypeEnum {

    DAY(1, "cacheDay"),
    HOURS(2, "cacheHours"),
    RELATED(3, "cacheDayRelated")
    ;

    private final int code;
    private final String desc;

    LimitTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static LimitTypeEnum getLimitTypeEnum(int limitType){
        return Arrays.stream(LimitTypeEnum.values()).filter(item -> item.getCode() == limitType).findFirst().orElseThrow(() -> new ApiCommonException("No matching constant to " + limitType));
    }
}
