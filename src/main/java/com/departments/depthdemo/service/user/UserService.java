package com.departments.depthdemo.service.user;

import com.departments.depthdemo.model.User;

import java.util.List;


public interface UserService {

    User saveUser(User user);

    List<User> getAll();

    User findUserByLoginId(String loginId);

    User findById(Long id);

    void delete(Long id);
}
