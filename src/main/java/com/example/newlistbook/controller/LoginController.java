package com.example.newlistbook.controller;

import com.example.newlistbook.mapper.EmpMapper;
import com.example.newlistbook.pojo.Result;
import com.example.newlistbook.pojo.User;
import com.example.newlistbook.service.EmpService;
import com.example.newlistbook.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 李圣
 * @Date:2023/4/20 15:10
 * @Version: 1.0
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;


    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("员工登录：{}",user);
        User u =  empService.login(user);
        if (u != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getName());
            claims.put("username",u.getUsername());

            //jwt包含了当前登录的员工信息
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        return Result.error("输入错误");
    }
}
