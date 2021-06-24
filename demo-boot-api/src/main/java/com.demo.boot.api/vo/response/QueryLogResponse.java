package com.demo.boot.api.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class QueryLogResponse {

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "PE报告管理状态 / 审核状态")
    private String status;
}
