package com.wenyao.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.pagehelper.PageHelper;
import com.wenyao.constant.PageConst;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class ServiceImplAspect {


    @Before("execution(* com.wenyao.service.impl.*.*(..))")
    public void setPage(JoinPoint joinPoint) {
        RpcContext context = RpcContext.getContext();
        Map<String, String> attachments = context.getAttachments();
        if ("true".equalsIgnoreCase(attachments.get("isPagination"))) {
            int pageNo = null == attachments.get("pageNo") ? PageConst.PAGE_NO : Integer.valueOf(attachments.get("pageNo"));
            int pageSize = null == attachments.get("pageSize") ? PageConst.PAGE_SIZE : Integer.valueOf(attachments.get("pageSize"));
            PageHelper.startPage(pageNo, pageSize);
        }
    }
}
