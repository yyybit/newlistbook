package com.example.newlistbook.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 19:32
 * @Version: 1.0
 */
//分页查询
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class PageBean {
    private Long total;
    private List rows;
}
