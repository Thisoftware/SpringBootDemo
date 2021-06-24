package com.demo.boot.api.vo.request;

import com.demo.boot.api.vo.PageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)//chain set,example: new QueryLogRequest().setId("").setUserId("")
public class QueryLogRequest extends PageVo {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "用户Id")
    private Long busId;

    @ApiModelProperty(value = "状态")
    private String status;
}
