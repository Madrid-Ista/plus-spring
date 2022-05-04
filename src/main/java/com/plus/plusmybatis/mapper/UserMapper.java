package com.plus.plusmybatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @Author: Madridista
 * @Date: 2022/5/4
 */
public interface UserMapper {

    @Select("select 'user'")
    String getUserName();
}
