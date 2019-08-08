package com.departments.depthdemo.service.paginator;

import com.departments.depthdemo.dto.InputPaginationDataDto;
import com.departments.depthdemo.model.OutputPaginationData;
import com.departments.depthdemo.service.employee.EmployeeService;
import com.departments.depthdemo.service.PageRequestData;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Setter
public class PagablePaginator implements Paginator {

    @Autowired
    EmployeeService employeeService;


    private int getTotalRecordCount(String filterValue)
    {
        int totalRecordCount;
        if(filterValue.length() == 0)
            totalRecordCount = employeeService.getEmployeeCount();
        else  totalRecordCount = employeeService.getFilteredEmployeeCount(filterValue);

        return totalRecordCount;
    }

    private int calculatePagesCount(int totalRecordCount, int recordsOnPage) {

        int countOfPages;
        float divisionPageResult = (float) totalRecordCount / (float) recordsOnPage;
        if (Math.ceil(divisionPageResult) == divisionPageResult)
            countOfPages = (int) Math.ceil(totalRecordCount / recordsOnPage);
        else countOfPages = (int) (divisionPageResult) + 1;

        return countOfPages;
    }

    private int calculateBooksCount(int countOfPages, int pagesInBook) {

        int countOfBooks;
        float divisionBookResult = (float) countOfPages / (float) pagesInBook;
        if (Math.ceil(divisionBookResult) == divisionBookResult)
            countOfBooks = (int) Math.ceil(countOfPages / pagesInBook);
        else countOfBooks = (int) (divisionBookResult) + 1;

        return countOfBooks;
    }

    @Override
    public PageRequestData getPaginationDataForPage(int pageNumber, InputPaginationDataDto inputData ) {

        int firstElementOnPage = 1;
        if (pageNumber > 1)
            firstElementOnPage = (pageNumber - 1) * inputData.getRecordsCountOnPage() + 1;

        PageRequestData pageRequestData = new PageRequestData();

        pageRequestData.setBeginElement(firstElementOnPage);
        pageRequestData.setRecordsCountOnPage(inputData.getRecordsCountOnPage());
        pageRequestData.setSortedColumnNames(inputData.getSortedColumnNames());
        pageRequestData.setFilterValue(inputData.getFilterValue());

        return pageRequestData;
    }

    @Override
    public OutputPaginationData getOutputPaginationData(InputPaginationDataDto inputData) {

        int recordsCountOnPage = inputData.getRecordsCountOnPage();
        int pagesCountInBook = inputData.getPageCountOnBook();
        int totalRecordCount = getTotalRecordCount(inputData.getFilterValue());
        int countOfPages = calculatePagesCount(totalRecordCount, recordsCountOnPage);
        int countOfBooks = calculateBooksCount(countOfPages, pagesCountInBook);
        int beginPage = 1;

        int currentPageNumber = inputData.getCurrentPageNumber();
        int currentBookNumber = 1;
        if (currentPageNumber > 1) {
            currentBookNumber = (int) Math.ceil((float) currentPageNumber / (float) pagesCountInBook);
        }
        if (currentBookNumber > 1) beginPage = (currentBookNumber - 1) * pagesCountInBook + 1;

        OutputPaginationData outputPaginationData
                = new OutputPaginationData(totalRecordCount, pagesCountInBook, countOfPages, countOfBooks
                , currentBookNumber, currentPageNumber, beginPage, recordsCountOnPage);

        return outputPaginationData;
    }
}
