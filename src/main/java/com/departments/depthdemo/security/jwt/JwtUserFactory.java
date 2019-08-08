package com.departments.depthdemo.security.jwt;


import com.departments.depthdemo.model.Role;
import com.departments.depthdemo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                Long.valueOf(user.getId()),
                user.getLoginId(),
                user.getName(),
                user.getName(),
                null,
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<Role>(user.getRoles())),
                true,
                null
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
