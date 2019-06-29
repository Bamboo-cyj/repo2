package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.MenuDao;
import com.fengk.dao.PermissionDao;
import com.fengk.dao.RoleDao;
import com.fengk.dao.UserDao;
import com.fengk.pojo.Menu;

import com.fengk.pojo.Role;
import com.fengk.pojo.User;

import com.fengk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 *
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class HealthUserServiceImpl implements UserService {
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    MenuDao menuDao;


    @Override
    public List<Menu> getMenu(String username) {

        return menuDao.getMenu(username);


    }

    @Override
    public User getUser(String s) {
        System.out.println("进入了service");
        User user = new User();
        user.setUsername(s);
        System.out.println("查询前"+user);
        user = userDao.getUser(user);
        System.out.println("查询后"+user);
        if (user==null){
            return null;
        }
        Set<Role> roles = roleDao.getRoles(user.getId());
        System.out.println(roles);

        user.setRoles(roles);
        return user;
    }
}
