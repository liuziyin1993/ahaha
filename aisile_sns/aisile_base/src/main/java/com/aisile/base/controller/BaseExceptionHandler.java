package com.aisile.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 简单· on 2019/4/19.
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.toString());
    }

}
