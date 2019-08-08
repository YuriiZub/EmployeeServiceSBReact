package com.departments.depthdemo.service.paginator;


import com.departments.depthdemo.dto.InputPaginationDataDto;
import com.departments.depthdemo.model.OutputPaginationData;
import com.departments.depthdemo.service.PageRequestData;

public interface Paginator {
    PageRequestData getPaginationDataForPage(int pageNumber, InputPaginationDataDto inputData);
    OutputPaginationData getOutputPaginationData(InputPaginationDataDto inputData);
}
