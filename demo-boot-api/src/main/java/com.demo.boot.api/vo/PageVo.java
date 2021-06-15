package com.demo.boot.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageVo {

    /**
     * 分页条数
     */
    @ApiModelProperty(value = "分页条数" ,name = "pageSize",example = "")
    private Integer pageSize;
    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数" ,name = "pageIndex",example = "")
    private Integer pageIndex;

    public Integer getPageSize() {
        if(pageSize == null || pageSize <= 0){
            return 10;
        }
        return pageSize;
    }
    public Integer getPageIndex() {
        if(pageIndex == null || pageIndex <= 0){
            return 1;
        }
        return pageIndex;
    }

}
