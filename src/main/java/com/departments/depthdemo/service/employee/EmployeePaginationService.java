package com.departments.depthdemo.service.employee;

import com.departments.depthdemo.dto.InputPaginationDataDto;
import com.departments.depthdemo.model.Employee;
import com.departments.depthdemo.model.OutputEmployeePage;
import com.departments.depthdemo.model.OutputPaginationData;
import com.departments.depthdemo.service.PageRequestData;
import com.departments.depthdemo.service.paginator.PaginationService;
import com.departments.depthdemo.service.paginator.Paginator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeepagination")
public class EmployeePaginationService implements PaginationService {

    @Autowired
    Paginator paginator;

    @Autowired
    EmployeeService employeeService;

    @Override
    public OutputEmployeePage getEmployeePage(InputPaginationDataDto inputData) {

        PageRequestData currentPageRequest = getCurrentPageRequest(inputData);

        int beginPosition = currentPageRequest.getBeginElement();
        int recordsOnPage = currentPageRequest.getRecordsCountOnPage();
        int totalRecordCount;
        if (inputData.getFilterValue().length() == 0)
            totalRecordCount = employeeService.getEmployeeCount();
        else totalRecordCount = employeeService.getFilteredEmployeeCount(inputData.getFilterValue());

        int endPosition;
        if ((beginPosition + recordsOnPage) > totalRecordCount)
            endPosition = totalRecordCount;
        else endPosition = beginPosition + recordsOnPage - 1;

        String sortedColumnNames = inputData.getSortedColumnNames();
        String filterValue = inputData.getFilterValue();

        PageRequestData pageRequestData = new PageRequestData(beginPosition, endPosition, sortedColumnNames, filterValue);

        List<Employee> outputList = employeeService.getPagedEmployee(pageRequestData);

        OutputPaginationData outputPaginationData = paginator.getOutputPaginationData(inputData);
        outputPaginationData.setRecordsCountOnPage(outputList.size());
        OutputEmployeePage outputEmployeePage = new OutputEmployeePage(outputList, outputPaginationData);

        return outputEmployeePage;
    }

    private PageRequestData getCurrentPageRequest(InputPaginationDataDto inputData) {

        int currentPageNumber = inputData.getCurrentPageNumber();
        return paginator.getPaginationDataForPage(currentPageNumber, inputData);
    }

}
