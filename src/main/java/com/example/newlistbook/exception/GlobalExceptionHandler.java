package com.example.newlistbook.exception;

import com.example.newlistbook.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: 李圣
 * @Date:2023/4/25 17:48
 * @Version: 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  //捕获所有异常
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起，操作失败");

    }}
