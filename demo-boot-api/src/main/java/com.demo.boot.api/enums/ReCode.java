package com.demo.boot.api.enums;

import com.demo.boot.api.constants.ReData;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * Created by Administrator on 2017/4/25 0025.
 */
public enum ReCode {

    FAIL("0000","操作失败"),

    SUCCESS("200","操作成功"),

    SystemError("100001","系统错误"),

    ObdActivePasswordError("400002","密码错误"),


    SoloSerialNoNotExits("400004","序列号不存在"),

    SoloNotAction("400005","请先激活设备"),

    SoloIpRefuse("400006","IP拒绝访问");

    ReCode(String code, String message){
        this.code=code;
        this.message =message;
    }

    private String code;

    private String message;

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public <T> void setResponse(ReData<T> response){
        if(response != null){
            response.setCode(code);
            response.setMessage(message);
        }
    }

    // msg附带具体信息
    public <T> void setResponse(ReData<T> response, String detailMsg) {
        if (response != null) {
            response.setCode(code);
            detailMsg = StringUtils.isNotBlank(detailMsg) ? (": " + detailMsg) : "";
            response.setMessage(message + detailMsg);
        }
    }
}
