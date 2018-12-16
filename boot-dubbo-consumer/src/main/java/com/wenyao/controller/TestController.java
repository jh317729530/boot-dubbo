package com.wenyao.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.github.pagehelper.Page;
import com.wenyao.annontation.Pagination;
import com.wenyao.domain.RpcResult;
import com.wenyao.entity.User;
import com.wenyao.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {


    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}")
    private TestService testService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        RpcContext context = RpcContext.getContext();
//        boolean consumerSide = context.isConsumerSide();
        return testService.sayHello(name);
    }

    @RequestMapping("getUser")
    public String getUser() {
        RpcResult<User> result = testService.select();
        System.out.println(result.getObj().getName());
        System.out.println("test");
        return result.getObj().getHeadImgUrl();
    }

    @Pagination
    @RequestMapping("getAllUser")
    public RpcResult<Page<User>> getAllUser() {
        User user = new User();
//        user.setPage(pageNo);
//        user.setRows(rows);
//        // 测试隐式传参
//        Map<String, String> attachements = new HashMap<>();
//        attachements.put("isPagination", "true");
//        attachements.put("pageNo", pageNo.toString());
//        attachements.put("pageSize", rows.toString());
//        RpcContext.getContext().setAttachments(attachements);
        RpcResult<Page<User>> pageRpcResult = testService.selectAll(user);
        Page<User> obj = pageRpcResult.getObj();
        int pageNum = obj.getPageNum();
        return pageRpcResult;
    }
}
