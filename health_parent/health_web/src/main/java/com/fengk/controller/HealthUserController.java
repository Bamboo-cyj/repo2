package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.fengk.entity.Result;
import com.fengk.pojo.Menu;
import com.fengk.service.UserService;
import com.fengk.utils.MessageConstant;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping(value = "/user")
public class HealthUserController {

    @Reference
    UserService userService;

    @RequestMapping(value = "/getUsernameAndMenu")
    public Result getUsernameAndMenu(){
        System.out.println("进入查找用户名");
        try {
            User securityUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (securityUser!=null){
                String username=securityUser.getUsername();
                Map<String,Object> map=new HashMap<>();
                map.put("username",username);
                List<Menu> menuList=userService.getMenu(username);
                map.put("menuList",menuList);


                return new Result(true, MessageConstant.GET_MENU_SUCCESS, map);
            }else {
                return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
            }

        } catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);

        }
    }
}
