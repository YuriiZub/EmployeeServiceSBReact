package com.departments.depthdemo.service.employee;

import com.departments.depthdemo.err.EmployeeAlreadyExistsException;
import com.departments.depthdemo.err.EmployeeNotFoundException;
import com.departments.depthdemo.model.Employee;
import com.departments.depthdemo.mapper.EmployeeMapper;
import com.departments.depthdemo.dto.EmployeeDto;
import com.departments.depthdemo.service.PageRequestData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yurii on 6/22/2019.
 */
@Service("employeeservice")
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {

        return employeeMapper.selectEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByName(String employeeName) {

        return employeeMapper.selectEmployeeByName(employeeName);
    }

    @Override
    public List<Employee> getAllEmployee() {

        return employeeMapper.selectAllEmployees();
    }

    @Override
    public List<Employee> getPagedEmployee(PageRequestData pageRequestData) {

        if (pageRequestData.getFilterValue().length() == 0) {
            return employeeMapper.selectPagedEmployees(pageRequestData);
        } else {

            String filter = pageRequestData.getFilterValue();
            filter = "%" + filter + "%";
            pageRequestData.setFilterValue(filter);

            return employeeMapper.selectFindPagedEmployees(pageRequestData);
        }
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {

        String employeeName = employeeDto.getName();
        List existEmployees = employeeMapper.selectEmployeeByName(employeeName);

        System.out.println("before saving");

        if (!existEmployees.isEmpty()) throw new EmployeeAlreadyExistsException(employeeName);

        employeeMapper.insertEmployee(employeeDto);

        Employee savedEmployee = employeeMapper.selectEmployeeByName(employeeDto.getName()).get(0);

        return savedEmployee;
    }

    @Override
    public Integer updateEmployee(EmployeeDto employeeDto) {

        int updatedRecords =  employeeMapper.updateEmployee(employeeDto);

        System.out.println("updated records = " + updatedRecords);

        if(updatedRecords == 0) throw new EmployeeNotFoundException("Id = " + employeeDto.getId());

        System.out.println("updated records before return" );

        return updatedRecords;
    }

    @Override
    public String deleteEmployeeById(Integer employeeId) {

        employeeMapper.deleteEmployeeById(employeeId);

        String resultString = "Employee with Id: " + employeeId.toString() + " was deleted successfully";

        return resultString;
    }

    @Override
    public Integer getEmployeeCount() {
        return employeeMapper.selectEmployeesCount();
    }

    @Override
    public Integer getFilteredEmployeeCount(String filterValue) {

        filterValue = "%" + filterValue + "%";

        return employeeMapper.selectFilteredEmployeesCount(filterValue);
    }

}
