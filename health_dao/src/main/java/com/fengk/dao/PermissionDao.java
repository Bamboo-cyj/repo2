package com.fengk.dao;

import com.fengk.pojo.Permission;
import com.github.pagehelper.Page;

import java.util.List;

public interface PermissionDao {
    Page<Permission> findPageByCondition(String queryString);

    void add(Permission permission);

    void delete(Integer id);

    void edit(Permission permission);

    Permission findById(Integer id);

    List<Permission> findAll();
}
