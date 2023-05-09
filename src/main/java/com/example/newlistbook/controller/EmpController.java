package com.example.newlistbook.controller;

import com.example.newlistbook.anno.LOG;
import com.example.newlistbook.pojo.PageBean;
import com.example.newlistbook.pojo.Result;
import com.example.newlistbook.pojo.User;
import com.example.newlistbook.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:19
 * @Version: 1.0
 */
@Slf4j
@RequestMapping("emps")@RestController
public class EmpController {

    @Autowired
    private EmpService empService;


    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询，参数：{}，{},{}，{},{}，{}",page,pageSize,name,gender,begin,end);
       PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
       return Result.success(pageBean);
    }

    @LOG
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除，ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }
    @LOG
    @PostMapping
    public Result save(@RequestBody User user){
        log.info("新增员工，emp:{}",user);
        empService.save(user);
        return Result.success();
    }

    @LOG
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息,id：{}"+id);
        User emp = empService.getById(id);
        return Result.success(emp);
    }

    @LOG
    @PutMapping()
    public Result update(@RequestBody User user){
        log.info("更新的员工信息为:",user);
        empService.update(user);
        return Result.success();
    }
}
