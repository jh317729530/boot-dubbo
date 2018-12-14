package com.wenyao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wenyao.domain.RpcResult;
import com.wenyao.entity.User;
import com.wenyao.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("getAllUser")
    public RpcResult<List<User>> getAllUser(Integer pageNo, Integer rows) {
        User user = new User();
        user.setPage(pageNo);
        user.setRows(rows);
        return testService.selectAll(user);
    }
}
