package com.example.newlistbook.mapper;

import com.example.newlistbook.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:13
 * @Version: 1.0
 */
@Mapper
public interface DeptMapper {

    //查询全部部门数据
    @Select("select * from dept")
    List<Dept> list();

    //根据ID删除部门
    @Delete("delete  from dept where id=#{id}")
    void deleteByID(Integer id);

    //新增部门
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);
}
