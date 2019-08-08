package com.departments.depthdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputEmployeePage {

    private List<Employee> pageWithData;
    private OutputPaginationData outputPaginationData;
}
