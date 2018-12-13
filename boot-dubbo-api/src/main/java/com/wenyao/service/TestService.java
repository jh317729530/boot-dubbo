package com.wenyao.service;

import com.wenyao.domain.RpcResult;
import com.wenyao.entity.User;

public interface TestService {

    String sayHello(String name);

    RpcResult<User> select();
}
