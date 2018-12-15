package com.wenyao.exception;

/**
 * 自定义的业务异常
 * @author ganjunhui
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String[] errParam;


    public ServiceException(String... errParam) {
        super();
        this.errParam = errParam;
    }

    public ServiceException() {
        super();
    }


    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errParam = new String[]{message};
    }


    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errParam = new String[]{message};
    }


    public ServiceException(String message) {
        super(message);
        this.errParam = new String[]{message};
    }


    public ServiceException(Throwable cause) {
        super(cause);
    }

    public String[] getErrParam() {
        return errParam;
    }



}
