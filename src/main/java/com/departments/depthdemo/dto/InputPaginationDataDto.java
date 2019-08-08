package com.departments.depthdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Class for input data from web page
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPaginationDataDto {

    @NotNull
    private int recordsCountOnPage;
    @NotNull
    private int pageCountOnBook;
    @NotNull
    private int currentPageNumber;
    @NotNull
    private String sortedColumnNames;
    @Null
    String filterValue;
}
