package com.departments.depthdemo.controller;

import com.departments.depthdemo.dto.EmployeeDto;
import com.departments.depthdemo.mapper.EmployeeMapper;
import com.departments.depthdemo.service.employee.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URISyntaxException;

/**
 * Created by Yurii on 6/22/2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EmployeeController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class EmployeeControllerTest {

    private EmployeeMapper employeeMapper;

    public EmployeeControllerTest(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    EmployeeService employeeService;

    @LocalServerPort
    private int localPort;

    private String baseUrl = "http://localhost:";

    private HttpEntity<String> request;

    @Before
    public void prepareData() throws URISyntaxException {
        localPort = 8085;
    }

    @Test
    public void getAllEmployees() throws Exception {
    }

    @Test
    public void getEmployeeById() throws Exception {
    }

    @Test
    public void getEmployeeByName() throws Exception {
    }

    @Test
    public void addNewEmployee() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        EmployeeDto employeeDto = new EmployeeDto(10,"Sydorenko Andrii",true,1);
        HttpEntity<EmployeeDto> request = new HttpEntity<>(employeeDto);
       // HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
       // ResponseEntity<Object> response = restTemplate.postForObject(new URI(baseUrl + localPort + "/employee/add"), request, Object.class);

    }

    @Test
    public void editEmpoyee() throws Exception {
    }

    @Test
    public void deleteEmployeeById() throws Exception {
    }

}