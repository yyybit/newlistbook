package com.example.newlistbook.mapper;

import com.example.newlistbook.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.format.annotation.DateTimeFormat;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 李圣
 * @Date:2023/4/13 14:19
 * @Version: 1.0
 */
@Mapper
public interface EmpMapper {

//    //查询数据
//    @Select("select count(*) from emp")
//    public Long count();

//    //分页查询获取数据
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<User> page(Integer start,Integer pageSize);
    //@Select("select * from emp")
    public List<User> list(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除
    void delete(List<Integer> ids);

    //新增员工
    void insert(User user);

    //根据员工查询
    @Select("select * from emp where id=#{id}")
    User getById(Integer id);

    //更新员工数据
    void update(User user);

    //员工查询
    @Select("select * from emp where username =#{username} and password = #{password}")
    User getByusernameandpassword(User user);

    //根据部门ID删除该部门下的员工数据
    @Delete("delete  from emp where dept_id= #{deptId}")
    void deleteByDeptId(Integer deptId);
}
