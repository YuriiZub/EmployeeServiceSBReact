package com.departments.depthdemo.service.department;

import com.departments.depthdemo.model.Department;
import com.departments.depthdemo.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yurii on 6/22/2019.
 */
@Service("departmentservice")
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getAllDepartments() {

        return departmentMapper.selectAllDepartments();
    }
}
