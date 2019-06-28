package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;

import com.fengk.pojo.Setmeal;



public interface HealthSetmealService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Setmeal setmeal, Integer[] checkitemIds);


    void addDatabaseRedis(String img);

    void addWebRedis(String filename);
}
