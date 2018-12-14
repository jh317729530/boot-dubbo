package com.wenyao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wenyao.domain.RpcResult;
import com.wenyao.entity.User;
import com.wenyao.mapper.UserMapper;
import com.wenyao.service.TestService;

import javax.annotation.Resource;
import java.util.List;


@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class TestServiceImpl implements TestService {

    @Resource
    private UserMapper userMapper;


    @Override
    public String sayHello(String name) {
        return "Hello," + name;
    }

    @Override
    public RpcResult<User> select() {
        User user = userMapper.select1();
        RpcResult<User> result = new RpcResult<>();
        result.setObj(user);
        return result;
    }

    @Override
    public RpcResult<List<User>> selectAll(User user) {
        if (user.getPage() != null && user.getRows() != null) {
            PageHelper.startPage(user.getPage(), user.getRows());
        }
        RpcResult<List<User>> result = new RpcResult<>();
        result.setObj(userMapper.selectAll());
        return result;
    }
}
