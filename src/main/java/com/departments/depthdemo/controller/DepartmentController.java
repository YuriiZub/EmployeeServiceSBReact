package com.departments.depthdemo.controller;

import com.departments.depthdemo.model.Department;
import com.departments.depthdemo.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Yurii on 6/21/2019.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(departmentService.getAllDepartments(), httpHeaders, HttpStatus.OK);
    }
}
