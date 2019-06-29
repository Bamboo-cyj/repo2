package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.Permission;

import java.util.List;

public interface PermissionService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Permission permission);

    void delete(Integer id);

    void edit(Permission permission);

    Permission findById(Integer id);

    List<Permission> findAll();
}
