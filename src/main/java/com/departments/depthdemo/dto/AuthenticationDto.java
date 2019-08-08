package com.departments.depthdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthenticationDto {
    @NotNull
    private String userLogin;
    @NotNull
    private String password;
}
