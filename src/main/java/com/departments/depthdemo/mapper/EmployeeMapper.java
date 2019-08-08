package com.departments.depthdemo.mapper;

import com.departments.depthdemo.model.Department;
import com.departments.depthdemo.model.Employee;
import com.departments.depthdemo.dto.EmployeeDto;
import com.departments.depthdemo.service.PageRequestData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by Yurii on 6/21/2019.
 */
@Component
@Mapper
public interface EmployeeMapper {

    @Select("with resp as (select empID, empName, empActive, emp_dpID  from tblemployees " +
            "where empName is not null)" +
            " select * from resp " +
            "order by ${sortedColumnNames} asc " +
            "limit #{beginElement}, #{recordsCountOnPage} ")
    @Results(value = {
            @Result(property = "id", column = "empID"),
            @Result(property = "name", column = "empName"),
            @Result(property = "active", column = "empActive"),
            @Result(property = "department", column = "emp_dpID", javaType = Department.class, one = @One(select = "getDepartment")),
    })
    List<Employee> selectPagedEmployees(PageRequestData pageRequestData);

    @Select("with resp as (select empID, empName, empActive, emp_dpID  from tblemployees " +
            "where empName Like  #{filterValue})" +
            " select * from resp " +
             "order by ${sortedColumnNames} asc " +
             "limit #{beginElement}, #{recordsCountOnPage} ")
    @Results(value = {
            @Result(property = "id", column = "empID"),
            @Result(property = "name", column = "empName"),
            @Result(property = "active", column = "empActive"),
            @Result(property = "department", column = "emp_dpID", javaType = Department.class, one = @One(select = "getDepartment")),
    })
    List<Employee> selectFindPagedEmployees(PageRequestData pageRequestData);

    @Select("select empID, empName, empActive, emp_dpID from tblemployees")
    @Results(value = {
            @Result(property = "id", column = "empID"),
            @Result(property = "name", column = "empName"),
            @Result(property = "active", column = "empActive"),
            @Result(property = "department", column = "emp_dpID", javaType = Department.class, one = @One(select = "getDepartment")),
    })
    List<Employee> selectAllEmployees();

    @Select("select empID, empName, empActive, emp_dpID from tblemployees where empID = #{empID}")
    @Results(value = {
            @Result(property = "id", column = "empID"),
            @Result(property = "name", column = "empName"),
            @Result(property = "active", column = "empActive"),
            @Result(property = "department", column = "emp_dpID", javaType = Department.class, one = @One(select = "getDepartment")),
    })
    Employee selectEmployeeById(@PathParam("empID") Integer employeeId);

    @Select("select empID, empName, empActive, emp_dpID from tblemployees where empName Like #{empName} || '%'")
    @Results(value = {
            @Result(property = "id", column = "empID"),
            @Result(property = "name", column = "empName"),
            @Result(property = "active", column = "empActive"),
            @Result(property = "department", column = "emp_dpID", javaType = Department.class, one = @One(select = "getDepartment")),
    })
    List<Employee> selectEmployeeByName(@PathParam("empName") String employeeName);

    @Select("select dpID, dpName from tblDepartments where dpID = #{dpId}")
    @Results(value = {
            @Result(property = "id", column = "dpID"),
            @Result(property = "name", column = "dpName")
    })
    Department getDepartment(@PathParam("dpId") Integer departmentId);

    @Insert("insert into tblEmployees (empName, empActive, emp_dpId ) " +
            "values (#{name}, #{active}, #{departmentId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "empID")
    void insertEmployee(EmployeeDto employeeDto);

    @Update("update tblEmployees " +
            "set empName = #{name}" +
            ", empActive = #{active}" +
            ", emp_dpID = #{departmentId} " +
            "where empID =  #{id}")
    void updateEmployee(EmployeeDto employeeDto);

    @Delete("delete from tblemployees where empID = #{dpId}")
    void deleteEmployeeById(@PathParam("dpId") Integer employeeId);

    @Select("select count(empID) from tblemployees")
    Integer selectEmployeesCount();

    @Select("select count(empID) from tblemployees " +
            "where empName Like #{filter} ")
    Integer selectFilteredEmployeesCount(@PathParam("filter") String filterValue);
}
