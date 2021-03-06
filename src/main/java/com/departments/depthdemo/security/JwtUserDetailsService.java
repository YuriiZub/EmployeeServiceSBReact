package com.departments.depthdemo.security;

import com.departments.depthdemo.model.User;
import com.departments.depthdemo.security.jwt.JwtUser;
import com.departments.depthdemo.security.jwt.JwtUserFactory;
import com.departments.depthdemo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userService.findUserByLoginId(loginId);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + loginId + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}
