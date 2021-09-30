package com.demo.boot.core.dao.source1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblPeReportManageLogMapper extends BaseMapper<TblPeReportManageLog> {

    List<TblPeReportManageLog> selectManageLog(String id);

    List<QueryLogResponse> selectCustReportApproveLogs (QueryLogRequest request);
}