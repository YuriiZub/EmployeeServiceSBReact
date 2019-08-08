package com.departments.depthdemo.service.paginator;


import com.departments.depthdemo.dto.InputPaginationDataDto;
import com.departments.depthdemo.model.OutputEmployeePage;

public interface PaginationService {
    OutputEmployeePage getEmployeePage(InputPaginationDataDto inputData);
}
