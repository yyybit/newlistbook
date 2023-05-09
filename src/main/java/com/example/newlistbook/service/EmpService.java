package com.example.newlistbook.service;

import com.example.newlistbook.pojo.PageBean;
import com.example.newlistbook.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:15
 * @Version: 1.0
 */
public interface EmpService {
    PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除
    void delete(List<Integer> ids);

    //新增员工
    void save(User user);

    //根据ID查询
    User getById(Integer id);

    //更新员工信息
    void update(User user);

    //实现员工登录
    User login(User user);
}
