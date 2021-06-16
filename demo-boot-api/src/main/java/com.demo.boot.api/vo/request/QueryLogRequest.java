package com.demo.boot.api.vo.request;

import com.demo.boot.api.vo.PageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryLogRequest extends PageVo {

    @ApiModelProperty(value = "ID")
    private String id;

}
