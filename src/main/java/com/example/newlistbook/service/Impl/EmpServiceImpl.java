package com.example.newlistbook.service.Impl;

import com.example.newlistbook.mapper.EmpMapper;
import com.example.newlistbook.pojo.PageBean;
import com.example.newlistbook.pojo.User;
import com.example.newlistbook.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:14
 * @Version: 1.0
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        //获取总记录数
//        Long count = empMapper.count();
//        //获取分页查询结果列表
//        Integer start = (page - 1)*pageSize;
//        List<User> emplist = empMapper.page(start,pageSize);
//        //封装pageBean对象
//        PageBean pageBean = new PageBean(count, emplist);
//        return pageBean;

        //1.设置分页参数
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<User> emplist = empMapper.list(name, gender, begin, end);
        Page<User> p = (Page<User>) emplist;
        //封装pageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;

    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void save(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        empMapper.insert(user);
    }

    @Override
    public User getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());

        empMapper.update(user);
    }

    @Override
    public User login(User user) {
        return empMapper.getByusernameandpassword(user);
    }
}
