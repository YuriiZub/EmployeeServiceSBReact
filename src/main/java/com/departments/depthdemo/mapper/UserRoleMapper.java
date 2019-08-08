package com.departments.depthdemo.mapper;

import com.departments.depthdemo.model.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserRoleMapper {
    @Insert("insert into users_roles (userId, roleId  ) " +
            "values (#{userId}, #{roleId})")
    @Options(useGeneratedKeys = true)
    void setUserRoleInfo(UserRole userRole);
}
