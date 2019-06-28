package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.CheckGroup;

import java.util.List;

public interface HealthCheckGroupService {
    PageResult findPageGroup(QueryPageBean queryPageBean);

    void add(CheckGroup checkGroup, Integer[] checkitemIds);


    List<Integer> getCheckItemGroupById(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    CheckGroup getCheckGroupById(Integer id);

    void delete(Integer id);

    List<CheckGroup> findAllGroup();
}
