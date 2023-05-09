package com.example.newlistbook.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.newlistbook.mapper.OperateLogMapper;
import com.example.newlistbook.pojo.OperateLog;
import com.example.newlistbook.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author: 李圣
 * @Date:2023/4/28 0:41
 * @Version: 1.0
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.newlistbook.anno.LOG)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        //操作人ID  -  当前登录员工ID
            //获取请求头的JWT令牌，解析令牌
        String jwt = request.getHeader("token");
        //Claims存放的是JWT定义的格式
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser =(Integer) claims.get("id");
        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();
        //操作方法名   目标签名
        String methodname = joinPoint.getSignature().getName();

        //操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        long begin = System.currentTimeMillis();
        //调用原始目标方法运行
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        //方法返回值
        String returnValue = JSONObject.toJSONString(result);
        //操作耗时
        long costTime = end - begin;


        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodname,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP操作记录日志：{}",operateLog);
        return result;
    }
}
