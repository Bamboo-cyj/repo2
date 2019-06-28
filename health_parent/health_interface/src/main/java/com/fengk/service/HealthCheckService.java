package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.CheckItem;
import com.fengk.pojo.HealthOrderTest;

import java.util.List;

public interface HealthCheckService {
    int save(HealthOrderTest healthOrderTest);

    List<HealthOrderTest> findAll();

    PageResult findPage(QueryPageBean queryPageBean);

    void add(CheckItem checkItem);

    void delete(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAllItems();
}
