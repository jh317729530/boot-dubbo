package com.wenyao.utils;

import com.github.pagehelper.Page;

public class PageUtil {

    public static <T> com.wenyao.domain.Page<T> page2page(Page<T> page) {
        com.wenyao.domain.Page<T> p = new com.wenyao.domain.Page<>();
        p.setPageNo(page.getPageNum());
        p.setPageSize(page.getPageSize());
        p.setStartRow(page.getStartRow());
        p.setEndRow(page.getEndRow());
        p.setTotal(page.getTotal());
        p.setPages(page.getPages());
        p.setResult(page.getResult());
        return p;
    }
}
