package com.departments.depthdemo.controller;

import com.departments.depthdemo.dto.UserDto;
import com.departments.depthdemo.model.User;
import com.departments.depthdemo.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    public ModelAndView getRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> createNewUser(@Validated(UserDto.New.class) @RequestBody UserDto user) {
        User userExists = userServiceImpl.findUserByLoginId(user.getLoginId());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (userExists != null) {
            return new ResponseEntity("User is already exist", httpHeaders, HttpStatus.CONFLICT);

        }
            User newUser = new User(null, user.getName(),user.getLoginId(),user.getPassword(), null ,true);
            userServiceImpl.saveUser(newUser);

        return new ResponseEntity("Sucess", httpHeaders, HttpStatus.OK);
    }

}
