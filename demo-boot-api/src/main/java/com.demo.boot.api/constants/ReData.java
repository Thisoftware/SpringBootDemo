package com.demo.boot.api.constants;

import com.demo.boot.api.enums.ReCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class ReData<T> implements Serializable{

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private String code;

    @JsonProperty("data")
    private T data;

    @JsonProperty("total")
    private long total;

    public void ok() {
        setCode(ReCode.SUCCESS.code());
        setMessage(ReCode.SUCCESS.message());
    }

    public void setData(T data) {
        ok();
        this.data = data;
    }

    public void pageData(T data, long total){
        ok();
        this.data = data;
        this.total = total;
    }
}
