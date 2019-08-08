package com.departments.depthdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @NotNull(groups = {UserDto.New.class})
    @Null(groups = {UserDto.LoginUser.class})
    private String name;
    @NotNull
    private String loginId;
    @NotNull
    private String password;

    public interface New {
    }

    interface Exist {
    }

    public interface LoginUser extends UserDto.Exist {
    }
}
