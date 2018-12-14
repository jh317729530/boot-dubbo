package com.wenyao.interceptor;

import com.alibaba.dubbo.rpc.RpcContext;
import com.wenyao.annontation.Pagination;
import com.wenyao.constant.PageConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class PageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (isNeedToPage(handler)) {
            RpcContext context = RpcContext.getContext();
            context.setAttachment("isPagination", "true");
            context.setAttachment("pageNo", getPageNo(request).toString());
            context.setAttachment("pageSize", getPageSize(request).toString());
        }
        return true;
    }

    /**
     * 通过判断handler上的注解获取是否分页
     * @param handler
     * @return
     */
    private boolean isNeedToPage(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Pagination pagination = handlerMethod.getMethodAnnotation(Pagination.class);
            if (null != pagination) {
                return pagination.value();
            }
        }
        return false;
    }

    /**
     * 获得pageNumber参数的值
     * @param request
     * @return
     */
    protected Integer getPageNo(HttpServletRequest request) {
        Integer pageNo = PageConst.PAGE_NO;
        try {
            String pg = request.getParameter(PageConst.PageParameter.PAGE_NO);
            if (StringUtils.isNotBlank(pg)) {
                pageNo =Integer.parseInt(pg);
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
        }
        return pageNo;
    }
    /**
     * 设置默认每页大小
     * @return
     */
    protected Integer getPageSize(HttpServletRequest request) {
        Integer pageSize = PageConst.PAGE_SIZE;
        try {
            String ps = request.getParameter(PageConst.PageParameter.PAGE_SIZE);
            if (StringUtils.isNotBlank(ps)) {
                pageSize =Integer.parseInt(ps);
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage(), e);
        }
        return pageSize;
    }
}
