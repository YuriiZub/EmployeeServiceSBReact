package com.departments.depthdemo.service.user;

import com.departments.depthdemo.mapper.RoleMapper;
import com.departments.depthdemo.mapper.UserMapper;
import com.departments.depthdemo.mapper.UserRoleMapper;
import com.departments.depthdemo.model.Role;
import com.departments.depthdemo.model.User;
import com.departments.depthdemo.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByLoginId(String loginId) {
        return userMapper.selectUserByLogin(loginId);
    }

    @Override
    public  User saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);

        userMapper.setUserInfo(user);

        Role role = roleMapper.getRoleInfo("USER");

        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());

        userRoleMapper.setUserRoleInfo(userRole);
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
