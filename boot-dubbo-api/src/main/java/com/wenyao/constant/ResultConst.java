package com.wenyao.constant;

public class ResultConst {

    public static class Status {
        /**
         * 失败
         */
        public static final String FALSE = "false";

        /**
         * 成功
         */
        public static final String TRUE = "true";

    }

    public static class Code {

        /**
         * 成功
         */
        public static final String SUCCESS = "200";

        /**
         * 找不到资源
         */
        public static final String NOT_FIND_RESOURCE = "404";

        /**
         * 系统异常
         */
        public static final String EXCEPTION = "500";

        /**
         * 验证错误
         */
        public static final String INVALID = "501";
    }


}
