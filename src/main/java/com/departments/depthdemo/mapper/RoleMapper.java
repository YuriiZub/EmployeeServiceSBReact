package com.departments.depthdemo.mapper;

import com.departments.depthdemo.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;

@Component
@Mapper
public interface RoleMapper {
    @Select("select roleId, roleName from roles where roleName = #{role}")
    @Results(value = {
            @Result(property = "id", column = "roleId"),
            @Result(property = "name", column = "roleName")
    })
    Role getRoleInfo(@PathParam("role") String roleName);
}
