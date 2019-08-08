package com.departments.depthdemo.mapper;

import com.departments.depthdemo.model.Role;
import com.departments.depthdemo.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.List;

@Component
@Mapper
public interface UserMapper {

    @Select("select usrId, username, password, active, LoginId from users where loginId = #{login}")
    @Results(value = {
            @Result(property = "id", column = "usrId"),
            @Result(property = "name", column = "username"),
            @Result(property = "loginId", column = "loginId"),
            @Result(property = "password", column = "password"),
            @Result(property = "active", column = "active"),
            @Result(property="roles", javaType=List.class, column="LoginId",
                            many=@Many(select="getUserRoles"))
    })
    User selectUserByLogin(@PathParam("login") String loginId);


    @Select("select roles.roleId, roles.roleName from roles, users, users_roles" +
            " where users_roles.userId = users.usrId " +
            " and roles.roleId = users_roles.roleId" +
            " and users.LoginId = #{login}")
   @Results(value = {
            @Result(property = "id", column = "roleId"),
            @Result(property = "name", column = "roleName")
    })
    List<Role> getUserRoles(@PathParam("login") String loginId);



    @Insert("insert into users (userName, LoginId, password, active ) " +
            "values (#{name}, #{loginId}, #{password}, #{active})")
    @Options(useGeneratedKeys = true, keyProperty = "id" , keyColumn = "usrId" )
    int setUserInfo(User user);





}
