package com.departments.depthdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


/**
 * Created by Yurii on 6/21/2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @Null(groups = {New.class})
    @NotNull(groups = {UpdateData.class})
    private Integer id;
    @NotNull(groups = {New.class, UpdateData.class})
    private String name;
    @NotNull(groups = {New.class, UpdateData.class})
    private Boolean active;
    @NotNull(groups = {New.class, UpdateData.class})
    private Integer departmentId;

    public interface New {
    }

    interface Exist {
    }

    public interface UpdateData extends Exist {
    }
}
