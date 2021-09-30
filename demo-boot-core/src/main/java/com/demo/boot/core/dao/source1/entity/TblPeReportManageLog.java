package com.demo.boot.core.dao.source1.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TblPeReportManageLog {
    private Long id;

    private String peReportManageStatus;

    private String userId;

    private String remarks;

    private String peReportManageId;

    private String creatorId;

    private Date createTime;

    private String updateId;

    private Date updateTime;

}
