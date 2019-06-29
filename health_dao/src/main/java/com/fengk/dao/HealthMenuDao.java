package com.fengk.dao;

import com.fengk.pojo.Menu;
import com.github.pagehelper.Page;

import java.util.List;

public interface HealthMenuDao {


    Page<Menu> findPageByCondition(String queryString);

    void add(Menu menu);

    void delete(Integer id);

    void edit(Menu menu);

    Menu findById(Integer id);

    List<Menu> findAll();
}
