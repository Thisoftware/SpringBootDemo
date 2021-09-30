package com.demo.boot.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.boot.api.vo.request.QueryLogRequest;
import com.demo.boot.api.vo.response.QueryLogResponse;
import com.demo.boot.core.dao.source1.entity.TblPeReportManageLog;
import com.demo.boot.core.dao.source1.mapper.TblPeReportManageLogMapper;
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
        log.info(JSON.toJSONString(demoMapper.selectBaseResource().size()));
        PageHelper.startPage(request.getPageIndex(),request.getPageSize());
        return new PageInfo<>(manageLogMapper.selectManageLog(request.getId()));
    }

    @Override
    public List<QueryLogResponse> queryCustReportLog(QueryLogRequest request) {
        return manageLogMapper.selectCustReportApproveLogs(request);
    }
}
