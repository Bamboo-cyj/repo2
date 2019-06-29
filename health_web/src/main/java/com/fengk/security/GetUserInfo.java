package com.fengk.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.pojo.Permission;
import com.fengk.pojo.Role;
import com.fengk.pojo.User;
import com.fengk.service.HealthOrderSettingService;
import com.fengk.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Component("getUserInfo")
public class GetUserInfo implements UserDetailsService {


    @Reference
    UserService userService;


    @Reference
    HealthOrderSettingService healthOrderSettingService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        healthOrderSettingService.findAllOrder("abc");
        System.out.println("传入的用户名"+s);
        User user=new User();
        user=userService.getUser(s);
        System.out.println("返回的user"+user);
        if (user==null){
            return null;
        }
        Set<Role> roles = user.getRoles();
        List<GrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
           list.add(new SimpleGrantedAuthority(role.getKeyword()));
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }

        System.out.println("权限列表"+list);
        org.springframework.security.core.userdetails.User securityUser=new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),list);


        return securityUser;
    }
}
