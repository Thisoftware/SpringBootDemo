package com.demo.boot.core.service.impl;

import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.mapper.TblPeReportManageLogMapper;
import com.demo.boot.core.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TblPeReportManageLogMapper manageLogMapper;

    @Override
    public PageInfo<TblPeReportManageLog> queryLog(QueryLogRequest request) {
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        return new PageInfo<>(manageLogMapper.selectManageLog(request.getId()));
    }

    @Override
    public List<QueryLogResponse> queryCustReportLog(QueryLogRequest request) {
        return manageLogMapper.selectCustReportApproveLogs(request);
    }
}
