package com.example.newlistbook.service.Impl;

import com.example.newlistbook.mapper.DeptMapper;
import com.example.newlistbook.mapper.EmpMapper;
import com.example.newlistbook.pojo.Dept;
import com.example.newlistbook.pojo.DeptLog;
import com.example.newlistbook.service.DeptLogService;
import com.example.newlistbook.service.DeptService;
import com.example.newlistbook.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:15
 * @Version: 1.0
 */
@Service
public class DeptServiceImpl implements DeptService {
    //注入Maper接口
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //交给spring进行事务管理
    @Override
    public void delete(Integer id) throws Exception {
        try {
            //根据id删除部门数据
            deptMapper.deleteByID(id);

            int i = 1/0;
//        if (true){
//            throw new Exception("出错了...");
//        }

            //根据部门ID删除该部门下的员工
            empMapper.deleteByDeptId(id);


        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作,此次解散的是"+id+"号部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }
}
