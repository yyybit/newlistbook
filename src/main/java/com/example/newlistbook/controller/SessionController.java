package com.example.newlistbook.controller;

import com.example.newlistbook.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 李圣
 * @Date:2023/4/20 17:09
 * @Version: 1.0
 */
@Slf4j
@RestController
public class SessionController {

    @GetMapping("/cl")
    public Result cookie1(HttpServletResponse response){
//        设置cookie
        response.addCookie(new Cookie("login_username","bank"));
        return Result.success();
    }

    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        //获取所有的Cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            //输出name为login——username的cookie
            if (cookie.getName().equals("login_username")){
                System.out.println("login_username:"+cookie.getValue());
            }
        }
        return Result.success();
    }
}
