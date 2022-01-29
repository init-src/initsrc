package com.initsrc.common.exception;

import com.initsrc.common.enums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.ShiroException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：异常捕捉类
 * 作者：INITSRC
 *
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //申明捕获哪个异常类
    public Map<String, Object> handlerException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ResultEnum.CODE_500.getCode());
        map.put("msg", ResultEnum.CODE_500.getMsg());
        String desc = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.toString();
        map.put("desc", desc);
        log.error("全局拦击，具体信息为：{}", e.getMessage());
        return map;
    }

    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> handlerBusinessException(BusinessException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("msg", e.getMsg());
        map.put("desc", e.getDesc());
        log.error("业务异常，具体信息为：{}", e.getMessage());
        return map;
    }

    @ExceptionHandler(ConstraintViolationException.class)  //申明捕获哪个异常类
    public Map<String, Object> ConstraintViolationException(Exception e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ResultEnum.CODE_400.getCode());
        map.put("msg", ResultEnum.CODE_400.getMsg());
        String desc = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : e.toString();
        map.put("desc", desc);
        log.error("异常：" + map, e);
        return map;
    }

    @ExceptionHandler(BindException.class)
    public Map<String, Object> BindException(BindException e) {
        return getResultMap(e.getBindingResult());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return getResultMap(e.getBindingResult());
    }

    private Map<String, Object> getResultMap(BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ResultEnum.CODE_400.getCode());
        if (bindingResult != null && bindingResult.getFieldError() != null) {
            StringBuffer error = new StringBuffer();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                error.append(fieldError.getDefaultMessage()).append(";");
            }
            map.put("msg", ResultEnum.CODE_400.getMsg());
            map.put("desc", error.toString());
        }
        log.error("参数异常，具体信息为：{}", bindingResult);
        return map;
    }

    /**
     * 捕捉所有Shiro异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ShiroException.class)
    public Map<String, Object> handle403(ShiroException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", ResultEnum.CODE_403.getCode());
        map.put("msg", ResultEnum.CODE_403.getMsg());
        log.error("SHIRO权限异常，具体信息为：{}", e.getMessage());
        return map;
    }

}
