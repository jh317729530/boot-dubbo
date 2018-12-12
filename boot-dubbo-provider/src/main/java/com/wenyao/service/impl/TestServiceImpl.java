package com.wenyao.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wenyao.service.TestService;


@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class TestServiceImpl implements TestService {


    @Override
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
