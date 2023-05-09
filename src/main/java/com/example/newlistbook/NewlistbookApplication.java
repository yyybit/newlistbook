package com.example.newlistbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//开启了对servlet组件的支持
@ServletComponentScan
@SpringBootApplication
public class NewlistbookApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewlistbookApplication.class, args);
    }

}
