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

    public ReData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReData(ReCode reCode) {
        this.code = reCode.code();
        this.message = reCode.message();
    }

    public ReData(String code, long total, T data) {
        this.data = data;
        this.total = total;
        this.code = code;
    }

    public ReData(T data) {
        this.data = data;
        this.message = ReCode.SUCCESS.message();
        this.code = ReCode.SUCCESS.code();
    }

    public ReData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ReData<T> success(){
        return new ReData<>(ReCode.SUCCESS.code(), ReCode.SUCCESS.message());
    }

    public static <T> ReData<T> success(T data){
        return new ReData<>(data);
    }

    public static <T> ReData<T> success(String message){
        return new ReData<>(ReCode.SUCCESS.code(), message);
    }

    public static <T> ReData<T> success(long total, T date){
        return new ReData<>(ReCode.SUCCESS.code(), total, date);
    }

    public static <T> ReData<T> fail(){
        return new ReData<>(ReCode.FAIL.code(), ReCode.FAIL.message());
    }

    public static <T> ReData<T> fail(String message){
        return new ReData<>(ReCode.FAIL.code(), message);
    }

    public static <T> ReData<T> fail(ReCode reCode){
        return new ReData<>(reCode);
    }
}
