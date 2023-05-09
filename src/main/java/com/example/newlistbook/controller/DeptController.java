package com.example.newlistbook.controller;

import com.example.newlistbook.anno.LOG;
import com.example.newlistbook.pojo.Dept;
import com.example.newlistbook.pojo.Result;
import com.example.newlistbook.service.DeptService;
import com.example.newlistbook.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:13
 * @Version: 1.0
 */
@Slf4j
@RequestMapping("/depts")
@RestController
@Service
public class DeptController {

    //private static Logger log = LoggerFactory.getLogger(DeptController.class)

    //注入Serive对象
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @LOG
    @GetMapping
    public Result list(){

        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    //记录日志
    @LOG
    //删除部门数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception{
        log.info("根据ID删除部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @LOG
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

}
