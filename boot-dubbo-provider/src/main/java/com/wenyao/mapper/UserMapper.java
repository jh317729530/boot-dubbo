package com.wenyao.mapper;

import com.wenyao.base.BaseMapper;
import com.wenyao.entity.User;

public interface UserMapper extends BaseMapper<User> {

    User select1();
}
