package com.wenyao.domain;

import com.wenyao.constant.RpcResultConst;
import lombok.Data;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

@Data
public class RpcResult<T> implements Serializable {

    /**
     * 接口调用成功，不需要返回对象
     */
    public static <T> RpcResult<T> newSuccess(){
        RpcResult<T> result = new RpcResult<>();
        result.setCode(RpcResultConst.Code.SUCCESS);
        result.setStatus(true);
        return result;
    }

    /**
     * 接口调用成功，有返回对象
     */
    public static <T> RpcResult<T> newSuccess(T obj) {
        RpcResult<T> result = newSuccess();
        result.setObj(obj);
        return result;
    }

    /**
     * 接口调用失败，有错误码和描述，没有返回对象
     */
    public static <T> RpcResult<T> newFailure(String message){
        RpcResult<T> result = new RpcResult<>();
        result.setCode(RpcResultConst.Code.FAILURE);
        result.setStatus(false);
        result.setMessage(message);
        return result;
    }

    /**
     * 接口调用失败，有错误字符串码和描述，没有返回对象
     */
    public static <T> RpcResult<T> newFailure(String error, String message){
        RpcResult<T> result = newFailure(message);
        result.setError(error);
        return result;
    }

    /**
     * 转换或复制错误结果
     */
    public static <T> RpcResult<T> newFailure(RpcResult<?> failure){
        RpcResult<T> result = new RpcResult<>();
        result.setCode(failure.getCode()!=0 ? failure.getCode() : -1);
        result.setError(failure.getError());
        result.setMessage(failure.getMessage());
        result.setException(failure.getException());
        return result;
    }

    /**
     * 接口调用失败，返回异常信息
     */
    public static <T> RpcResult<T> newException(Exception e){
        RpcResult<T> result = new RpcResult<>();
        result.setCode(RpcResultConst.Code.EXCEPTION);
        result.setException(e);
        result.setMessage(e.getMessage());
        return result;
    }

    private int code;
    private boolean status;
    private T obj;
    private String error;
    private String message;
    private Exception exception;


    public String toString() {
        StringBuilder result = new StringBuilder("Result");
        if(obj!=null) result.append("<"+obj.getClass().getSimpleName()+">");
        result.append(": {code="+code);
        if(obj!=null) result.append(", object="+obj);
        if(error!=null) result.append(", error="+error);
        if(message!=null) result.append(", message="+message);
        if(exception!=null) {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            result.append(", exception="+stringWriter.toString());
        }
        result.append(" }");
        return result.toString();
    }

}
