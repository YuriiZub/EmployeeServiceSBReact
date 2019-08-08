package com.departments.depthdemo.controller;

import com.departments.depthdemo.dto.InputPaginationDataDto;
import com.departments.depthdemo.model.Employee;
import com.departments.depthdemo.dto.EmployeeDto;
import com.departments.depthdemo.err.ErrorData;
import com.departments.depthdemo.model.OutputEmployeePage;
import com.departments.depthdemo.service.employee.EmployeeService;
import com.departments.depthdemo.service.paginator.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * Created by Yurii on 6/21/2019.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PaginationService paginationService;


    @GetMapping("/page/{recordsCountOnPage}/{pageCountOnBook}/{currentPageNumber}/{sortedColumnNames}/{filterValue}")
    public ResponseEntity<List<Employee>> selectFilteredEmployeePage(
            @PathVariable Integer recordsCountOnPage
            , @PathVariable Integer pageCountOnBook
            , @PathVariable Integer currentPageNumber
            , @PathVariable String sortedColumnNames
            , @PathVariable(required = false) String filterValue) {

        InputPaginationDataDto inputData = new InputPaginationDataDto(recordsCountOnPage, pageCountOnBook, currentPageNumber, sortedColumnNames, filterValue);
        OutputEmployeePage outputEmployeePage = paginationService.getEmployeePage(inputData);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(outputEmployeePage, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/page/{recordsCountOnPage}/{pageCountOnBook}/{currentPageNumber}/{sortedColumnNames}")
    public ResponseEntity<List<Employee>> selectEmployeePage(
            @PathVariable Integer recordsCountOnPage
            , @PathVariable Integer pageCountOnBook
            , @PathVariable Integer currentPageNumber
            , @PathVariable String sortedColumnNames) {

        InputPaginationDataDto inputData = new InputPaginationDataDto(recordsCountOnPage, pageCountOnBook, currentPageNumber, sortedColumnNames, "");
        OutputEmployeePage outputEmployeePage = paginationService.getEmployeePage(inputData);

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(outputEmployeePage, httpHeaders, HttpStatus.OK);
    }


    @GetMapping("/get/{employeeID}")
    public @ResponseBody
    ResponseEntity<Employee>
    getEmployeeById(@PathVariable String employeeID) {

        return new ResponseEntity(employeeService.getEmployeeById(Integer.parseInt(employeeID)), HttpStatus.OK);
    }

    @GetMapping("/count/")
    public @ResponseBody
    ResponseEntity<Integer>
    getEmployeeCount() {

        return new ResponseEntity(employeeService.getEmployeeCount(), HttpStatus.OK);
    }

    @GetMapping("/find/{employeeName}")
    public @ResponseBody
    ResponseEntity<List<Employee>>
    getEmployeeByName(@PathVariable String employeeName) {
        if ((employeeName.length() == 0) || (employeeName == null))
            return new ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK);
        return new ResponseEntity(employeeService.getEmployeeByName(employeeName), HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Employee> addnNewEmployee(@Validated(EmployeeDto.New.class) @RequestBody EmployeeDto employeeDto) throws Exception {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(employeeService.saveEmployee(employeeDto), httpHeaders, HttpStatus.OK);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Employee> editEmpoyee(@Validated(EmployeeDto.UpdateData.class) @RequestBody EmployeeDto employeeDto) throws Exception {
        return new ResponseEntity(employeeService.updateEmployee(employeeDto), httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<String>
    deleteEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        return new ResponseEntity(employeeService.deleteEmployeeById(employeeId), httpHeaders, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData> handleException(Exception e) {
        ErrorData errorData = new ErrorData(e.getMessage());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(errorData, httpHeaders, HttpStatus.BAD_REQUEST);
    }


}
