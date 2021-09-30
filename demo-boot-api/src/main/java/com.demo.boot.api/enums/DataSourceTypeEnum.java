package com.demo.boot.api.enums;

import lombok.Getter;

@Getter
public enum DataSourceTypeEnum {
    DATA_SOURCE1("source1"),
    DATA_SOURCE2("source2");

    private String code;

    DataSourceTypeEnum(String code){
        this.code = code;
    }
}
