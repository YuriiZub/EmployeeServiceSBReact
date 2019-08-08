package com.departments.depthdemo.controller;

import com.departments.depthdemo.dto.AuthenticationDto;
import com.departments.depthdemo.model.User;
import com.departments.depthdemo.security.jwt.JwtTokenProvider;
import com.departments.depthdemo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto requestDto) {
        System.out.println("try to login with : " + requestDto);

        try {
            String userLogin = requestDto.getUserLogin();
            System.out.println("try to find user : " + userLogin + ";" + requestDto.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin, requestDto.getPassword()));
            System.out.println("authenticationManager done something: ");
            User user = userService.findUserByLoginId(userLogin);
            System.out.println("userFound : " + user);
            if (user == null) {
                System.out.println("user not found");
                throw new UsernameNotFoundException("User with username: " + userLogin + " not found");
            }

            String token = jwtTokenProvider.createToken(userLogin, user.getRoles());
            System.out.println("token : " + token);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", userLogin);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            System.out.println("exception" + e);
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
