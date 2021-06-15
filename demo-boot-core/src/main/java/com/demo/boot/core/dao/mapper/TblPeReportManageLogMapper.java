package com.demo.boot.core.dao.mapper;


import com.demo.boot.core.dao.entity.TblPeReportManageLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblPeReportManageLogMapper{

    List<TblPeReportManageLog> selectManageLog(String id);

}