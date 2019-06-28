package com.fengk.dao;

import com.fengk.pojo.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getMenu(String username);
}
