package com.example.newlistbook;

import com.example.newlistbook.controller.DeptController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
class NewlistbookApplicationTests {

    @Autowired
    private ApplicationContext applicationContext; //IOC容器

    @Test
    public void testGetBean(){
        //根据Bean名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);

        //根据bean的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);

        //根据bean的名称及类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

        @Test
    public void testGenJwt(){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",1);
            claims.put("name","tom");
            //创建令牌
            String jwt = Jwts.builder()
                    //选择签名算法
                    .signWith(SignatureAlgorithm.HS256, "lisheng")
                    //自定义内容（载荷）
                    .setClaims(claims)
                    //令牌有效日期+1小时
                    .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                    .compact();
            System.out.println(jwt);
        }

    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("lisheng")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4MjA2NDQ5OH0.6trbvMh8108QjUx7lg8X9twK4K_L4nr4gROxqJv7A1Q")
                .getBody();
        System.out.println(claims);
    }

}

