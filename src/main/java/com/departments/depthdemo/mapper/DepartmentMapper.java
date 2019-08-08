package com.departments.depthdemo.mapper;

import com.departments.depthdemo.model.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by Yurii on 6/21/2019.
 */
@Component
@Mapper
public interface DepartmentMapper {
    @Select("select dpID, dpName from tblDepartments where dpID = #{dpID}")
    @Results(value = {
            @Result(property = "id", column = "dpID"),
            @Result(property = "name", column = "dpName"),
    })
    Department selectDepartmentById(@PathParam("dpID") Integer departmentId);

    @Select("select dpID, dpName from tblDepartments where dpName like #{dpName} || '%'")
    @Results(value = {
            @Result(property = "id", column = "dpID"),
            @Result(property = "name", column = "dpName"),
    })
    List<Department> selectDepartmentByName(@PathParam("dpName")String departmentName);

    @Select("select dpID, dpName from tblDepartments")
    @Results(value = {
            @Result(property = "id", column = "dpID"),
            @Result(property = "name", column = "dpName"),
    })
    List<Department> selectAllDepartments();
}
