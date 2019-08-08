package com.departments.depthdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutputPaginationData {

    private int totalRecordCount;
    private int pagesCountInBook;
    private int countOfPages;
    private int countOfBooks;
    private int currentBook;
    private int currentPage;
    private int beginPage;
    private int recordsCountOnPage;

}
