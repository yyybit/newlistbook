package com.example.newlistbook.service;

import com.example.newlistbook.pojo.Dept;
import com.example.newlistbook.pojo.PageBean;
import com.example.newlistbook.pojo.User;

import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:20
 * @Version: 1.0
 */

public interface DeptService {
    List<Dept> list();

    //删除部门
    void delete(Integer id) throws Exception;

    //新增部门
    void add(Dept dept);
}
