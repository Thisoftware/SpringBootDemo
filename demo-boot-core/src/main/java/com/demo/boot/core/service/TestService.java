package com.demo.boot.core.service;

import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TestService{
    /**
     * 查询管理日志列表
     * @param request
     * @return
     */
    PageInfo<TblPeReportManageLog> queryLog(QueryLogRequest request);

    /**
     * 查询客户日志
     * @param request
     * @return
     */
    List<QueryLogResponse> queryCustReportLog(QueryLogRequest request);
}
