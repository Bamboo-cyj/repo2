package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.CheckItem;
import com.fengk.pojo.Menu;

import java.util.List;

public interface HealthMenuService {
    PageResult findPage(QueryPageBean queryPageBean);


    void add(Menu menu);

    void delete(Integer id);

    void edit(Menu menu);


    Menu findById(Integer id);

    List<Menu> findAll();
}
