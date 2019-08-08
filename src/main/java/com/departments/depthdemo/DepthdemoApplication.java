package com.departments.depthdemo;

import com.departments.depthdemo.model.*;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MappedTypes({Department.class, Employee.class
		, Role.class, User.class, UserRole.class})
@MapperScan("com.departments.depthdemo.mapper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.departments.depthdemo"})
public class DepthdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepthdemoApplication.class, args);
	}

}
