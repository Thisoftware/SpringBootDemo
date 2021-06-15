package com.demo.boot.core.service;

import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import com.github.pagehelper.PageInfo;

public interface TestService{

    PageInfo<TblPeReportManageLog> queryLog(QueryLogRequest request);

}
