package com.demo.boot.core.service.impl;

import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.source1.mapper.TblPeReportManageLogMapper;
import com.demo.boot.core.dao.source2.entity.BaseResource;
import com.demo.boot.core.dao.source2.mapper.DemoMapper;
import com.demo.boot.core.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Resource
    private TblPeReportManageLogMapper manageLogMapper;

    @Resource
    private DemoMapper demoMapper;

    @Override
    public PageInfo<TblPeReportManageLog> queryLog(QueryLogRequest request) {
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        return new PageInfo<>(manageLogMapper.selectManageLog(request.getId()));
    }

    @Override
    public PageInfo<BaseResource> queryResource(QueryLogRequest request) {
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        return new PageInfo<>(demoMapper.selectBaseResource());
    }

    @Override
    public List<QueryLogResponse> queryCusReportLog(QueryLogRequest request) {
        log.info("资源数据有" + demoMapper.selectBaseResource().size() + "条..");
        return manageLogMapper.selectCustReportApproveLogs(request);
    }
}
