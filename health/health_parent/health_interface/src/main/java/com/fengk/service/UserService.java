package com.fengk.service;

import com.fengk.pojo.Menu;
import com.fengk.pojo.User;

import java.util.List;


public interface UserService {

    List<Menu> getMenu(String username);

    User getUser(String s);
}
