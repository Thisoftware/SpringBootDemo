package com.demo.boot.core.dao.source2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.boot.core.dao.source2.entity.BaseResource;

import java.util.List;

public interface DemoMapper extends BaseMapper<BaseResource> {

    List<BaseResource> selectBaseResource();

}
