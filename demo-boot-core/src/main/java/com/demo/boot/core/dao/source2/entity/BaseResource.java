package com.demo.boot.core.dao.source2.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResource {

    private String resourceId;

    /*
     * 资源名称
     */
    private String resourceName;

    /*
     * 资源编码
     */
    private String resourceCode;

    /*
     * 资源定位符
     */
    private String resourceUri;

    /**
     * 资源方法
     */
    private Integer resourceMethod;

    /*
     * 资源类型 0 链接 1 功能
     */
    private Integer resourceType;

    /**
     * 资源图标
     */
    private String resourceIcon;

    /*
     * 父资源id
     */
    private String parentId;

    /**
     * 排序
     */
    private Integer sort;

    private Date createdTime;

    private Date updatedTime;

    private String createdUser;

    private String updatedUser;

    private int deleted;
}
