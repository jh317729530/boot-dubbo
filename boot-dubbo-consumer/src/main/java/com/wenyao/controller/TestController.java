package com.wenyao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wenyao.domain.RpcResult;
import com.wenyao.entity.User;
import com.wenyao.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private TestService testService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return testService.sayHello(name);
    }

    @RequestMapping("getUser")
    public String getUser() {
        RpcResult<User> result = testService.select();
        System.out.println(result.getObj().getName());
        return result.getObj().getHeadImgUrl();
    }
}
