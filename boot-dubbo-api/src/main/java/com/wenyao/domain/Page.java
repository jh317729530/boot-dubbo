package com.wenyao.domain;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {


        private int pageNum;
        /**
         * 页面大小
         */
        private int pageSize;
        /**
         * 起始行
         */
        private int startRow;
        /**
         * 末行
         */
        private int endRow;
        /**
         * 总数
         */
        private long total;
        /**
         * 总页数
         */
        private int pages;

        private List<T> result;

}
