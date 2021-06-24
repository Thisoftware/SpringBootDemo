package com.demo.boot.core.dao.common;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface MyBaseMapper<T> extends BaseMapper<T>, MySqlMapper<T>, ConditionMapper<T>{
}
