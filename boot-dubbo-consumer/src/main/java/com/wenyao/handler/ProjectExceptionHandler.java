package com.wenyao.handler;

import com.wenyao.domain.Result;
import com.wenyao.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;
import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleValidationFailure(ConstraintViolationException ex) {
        Result result = new Result();
        StringBuilder messages = new StringBuilder();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            messages.append(violation.getMessage()).append("\n");
        }
        result.setInvalidMsg(messages.toString());
        return result;
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        Result result = new Result();
        //自定义业务异常
        if (e instanceof ServiceException) {
            result.setExceptionMsg(e.getMessage());
            return result;
            //Hibernate Validator 参数校验错误
        } else if(e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            String message = bindingResult.getFieldError().getDefaultMessage();
            result.setExceptionMsg(message);
            log.error(e.getMessage());
            return result;
        }
        // 缺少参数的异常
        if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            log.error(e.getMessage());
            result.setInvalidMsg("缺少参数[" + ex.getParameterName()+ "]");
            return result;
        }
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException ex = (MethodArgumentTypeMismatchException) e;
            MethodParameter parameter = ex.getParameter();
            String paramName = parameter == null ? "" : parameter.getParameterName();
            log.error("参数[" + paramName + "]类型不匹配", e);
            result.setInvalidMsg("参数[" + paramName+ "]类型不匹配");
            return result;
        }

        result.setExceptionMsg("系统发生异常");
        log.error("系统发生异常", e);
        return result;
    }

}
