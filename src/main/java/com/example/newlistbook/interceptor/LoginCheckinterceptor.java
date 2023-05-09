package com.example.newlistbook.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.newlistbook.pojo.Result;
import com.example.newlistbook.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 李圣
 * @Date:2023/4/24 18:50
 * @Version: 1.0
 */
@Slf4j
@Component  //ioc容器的bean对象
public class LoginCheckinterceptor implements HandlerInterceptor {
    @Override //目标资源方法运行前的，  返回true 放行 反之则不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {


// 1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求中的url：", url);

//2.判断请求url中是否包含login,如果包含，说明是登陆操作，放行
        if (url.contains("login")) {
            log.info("登录操作，放行");
            return true;
        }
//3.获取请求头中的令牌（token）
        String jwt = req.getHeader("token");

//4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录的信息");
            //拿到的错误结果
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象  对象转json -------->阿里巴巴fastJson
            String notLogin = JSONObject.toJSONString(error);
            //利用输出流相应出去
            resp.getWriter().write(notLogin);
            return false;
        }
//5.解析token，如果解析失败，返回错误结果
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录信息");
            //拿到的错误结果
            Result error = Result.error("NOT_LOGIN");
            //手动转换对象  对象转json -------->阿里巴巴fastJson
            String notLogin = JSONObject.toJSONString(error);
            //利用输出流相应出去
            resp.getWriter().write(notLogin);
            return false;
        }



//6.令牌合法 放行
        log.info("令牌合法，放行");
        return true;
    }




    @Override //目标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override //视图渲染后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterComletion");
    }
}
