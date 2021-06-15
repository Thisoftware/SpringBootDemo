package com.demo.boot.api.vo.request;

import com.demo.boot.api.vo.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QueryLogRequest extends PageVo {

    private String id;

}
