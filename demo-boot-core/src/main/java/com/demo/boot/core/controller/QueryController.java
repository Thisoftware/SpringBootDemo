package com.demo.boot.core.controller;

import com.demo.boot.api.annotation.Timer;
import com.demo.boot.api.constants.ReData;
import com.demo.boot.api.enums.ErrorCodeEnum;
import com.demo.boot.api.exception.ApiCommonException;
import com.demo.boot.api.path.BaseUrl;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import com.demo.boot.core.service.TestService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = BaseUrl.API_LOG, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"日志查询"})
@Slf4j
public class QueryController {

    @Resource
    private TestService testService;

    @ApiOperation(value = "日志列表查询", notes = "日志列表查询")
    @PostMapping(value = BaseUrl.QUERY_LOG)
    @Timer
    public ReData<List<TblPeReportManageLog>> queryLog(@RequestBody QueryLogRequest request) throws ApiCommonException {
        if(StringUtils.isBlank(request.getId())){
            throw new ApiCommonException(ErrorCodeEnum.REQUEST_PARAM_NULL_KEY);
        }
        ReData<List<TblPeReportManageLog>> response = new ReData<>();
        PageInfo<TblPeReportManageLog> pageInfo = testService.queryLog(request);
        response.setData(pageInfo.getList());
        response.setTotal(pageInfo.getTotal());
        return response;
    }
}
