package com.demo.boot.core.controller;

import com.demo.boot.api.annotation.Timer;
import com.demo.boot.api.constants.ReData;
import com.demo.boot.api.path.BaseUrl;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.source2.entity.BaseResource;
import com.demo.boot.core.service.TestService;
import com.demo.boot.core.util.ValidateUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    public ReData<List<TblPeReportManageLog>> queryLog(@RequestBody QueryLogRequest request) {
        ValidateUtil.validateNotNull(request, QueryLogRequest::getId);
        PageInfo<TblPeReportManageLog> pageInfo = testService.queryLog(request);
        return ReData.success(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "字典列表查询", notes = "字典列表查询")
    @PostMapping(value = BaseUrl.QUERY_RESOURCE)
    @Timer
    public ReData<List<BaseResource>> queryResource(@RequestBody QueryLogRequest request){
        PageInfo<BaseResource> pageInfo = testService.queryResource(request);
        return ReData.success(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "客户查询", notes = "客户查询")
    @PostMapping(value = BaseUrl.QUERY_CUS_REPORT)
    @Timer
    public ReData<List<QueryLogResponse>> queryCusReportLog(@RequestBody QueryLogRequest request){
        return ReData.success(testService.queryCusReportLog(request));
    }
}
