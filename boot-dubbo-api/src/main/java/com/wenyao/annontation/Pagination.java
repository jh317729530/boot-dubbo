package com.wenyao.annontation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 分页注解
 * 在controller的requestMapping方法上加上注解即可分页
 * @author ganjunhui
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface Pagination {

    /**
     * 是否分页
     * @return
     */
    boolean value() default true;

}
