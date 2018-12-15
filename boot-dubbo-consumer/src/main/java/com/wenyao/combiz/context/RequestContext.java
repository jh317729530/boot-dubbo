package com.wenyao.combiz.context;

import com.wenyao.constant.RequestConst;

import java.util.Optional;

public class RequestContext {

    private static final ThreadLocal<String> IP_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<String> REQUEST_URI_LOCAL = new ThreadLocal<>();


    /**
     * 获取系统访问的ip
     * @return
     */
    public static String getIp() {
        return Optional.ofNullable(IP_THREAD_LOCAL.get()).orElse(RequestConst.Ip.LOCAL_HOST);
    }

    /**
     * 设定ip
     * @param ip
     */
    public static void setIp(String ip) {
        IP_THREAD_LOCAL.set(ip);
    }

    /**
     * 移除ip
     */
    public static void removeIp() {
        IP_THREAD_LOCAL.remove();
    }


    public static String getRequestUri() {
        return Optional.ofNullable(REQUEST_URI_LOCAL.get()).orElse("");
    }


    public static void setRequestUri(String accessPath) {
        REQUEST_URI_LOCAL.set(accessPath);
    }


    public static void removeRequestUri() {
        REQUEST_URI_LOCAL.remove();
    }
}
