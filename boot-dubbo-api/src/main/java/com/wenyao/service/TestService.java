package com.wenyao.service;

import com.wenyao.domain.RpcResult;
import com.wenyao.entity.User;

import java.util.List;

public interface TestService {

    String sayHello(String name);

    RpcResult<User> select();

    RpcResult<List<User>> selectAll(User user);
}
