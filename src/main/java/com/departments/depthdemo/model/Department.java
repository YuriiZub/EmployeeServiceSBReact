package com.departments.depthdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Yurii on 6/21/2019.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Integer id;
    private String name;
}
