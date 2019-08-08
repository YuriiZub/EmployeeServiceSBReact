package com.departments.depthdemo.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestData {

    @NotNull
    private int beginElement;
    @NotNull
    private int recordsCountOnPage;
    @NotNull
    String sortedColumnNames;
    @Null
    String filterValue;
}
