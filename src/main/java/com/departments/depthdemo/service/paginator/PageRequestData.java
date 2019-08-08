package com.departments.depthdemo.service.paginator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@Getter
public class PageRequestData {

    @NotNull
    private int beginElement;
    @NotNull
    private int recordsCountOnPage;
    @NotNull
    String sortingColumnName;
}
