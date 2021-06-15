package com.demo.boot.core.dao.entity;

import com.demo.boot.api.vo.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TblPeReportManageLog extends PageVo {
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