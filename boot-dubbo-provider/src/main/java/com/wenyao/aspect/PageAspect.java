package com.wenyao.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import com.github.pagehelper.PageHelper;
import com.wenyao.constant.PageConst;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 分页切面
 */
@Aspect
@Component
public class PageAspect {

    /**
     * 分页切面 mapper包中，所有返回com.github.pagehelper.Page 对象的接口均使用此切面进行分页
     * @param joinPoint
     * @return
     */
    @Before("execution (com.github.pagehelper.Page com.wenyao.mapper.*.*(..))")
    public void around(JoinPoint joinPoint) {

        RpcContext context = RpcContext.getContext();
        Map<String, String> attachments = context.getAttachments();
        if ("true".equalsIgnoreCase(attachments.get("isPagination"))) {
            int pageNo = null == attachments.get("pageNo") ? PageConst.PAGE_NO : Integer.valueOf(attachments.get("pageNo"));
            int pageSize = null == attachments.get("pageSize") ? PageConst.PAGE_SIZE : Integer.valueOf(attachments.get("pageSize"));
            PageHelper.startPage(pageNo, pageSize, true, false, null);
        }
    }
}
