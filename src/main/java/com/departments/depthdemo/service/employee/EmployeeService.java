package com.departments.depthdemo.service.employee;

import com.departments.depthdemo.model.Employee;
import com.departments.depthdemo.dto.EmployeeDto;
import com.departments.depthdemo.service.PageRequestData;

import java.util.List;

/**
 * Created by Yurii on 6/22/2019.
 */
public interface EmployeeService {

    Employee getEmployeeById(int employeeID);

    List<Employee> getEmployeeByName(String employeeName);

    List<Employee> getAllEmployee();

    List<Employee> getPagedEmployee(PageRequestData pageRequestData);

    Employee saveEmployee(EmployeeDto employeeDto);

    Integer updateEmployee(EmployeeDto employeeDto);

    String deleteEmployeeById(Integer employeeID);

    Integer getEmployeeCount();

    Integer getFilteredEmployeeCount(String filterValue);

}
