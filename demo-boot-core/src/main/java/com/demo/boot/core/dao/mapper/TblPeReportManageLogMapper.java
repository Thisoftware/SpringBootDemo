package com.demo.boot.core.dao.mapper;


import com.demo.boot.core.dao.common.MyBaseMapper;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblPeReportManageLogMapper extends MyBaseMapper<TblPeReportManageLog> {

    List<TblPeReportManageLog> selectManageLog(String id);

    List<QueryLogResponse> selectCustReportApproveLogs (QueryLogRequest request);
}