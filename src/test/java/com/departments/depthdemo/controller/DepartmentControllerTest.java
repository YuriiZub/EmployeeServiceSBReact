package com.departments.depthdemo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by Yurii on 6/22/2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DepartmentController.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class DepartmentControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int localPort;

    private String baseUrl = "http://localhost:";

    private HttpEntity<String> request;

    @Before
    public void prepareData() throws URISyntaxException {
        localPort = 8085;
    }


    @Test
    public void getAllDepartments() throws Exception {

        ResponseEntity<Object> responseEntity =
                restTemplate.getForEntity(new URI(baseUrl + localPort + "/department/all"), Object.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}