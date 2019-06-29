package com.fengk.dao;

import com.fengk.pojo.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface HealthRoleDao {
    public Page<Role> findPageByCondition(String queryString);

    public void delete(Integer id);

    Role findById(Integer id);

    List<Integer> getPermissionRoleById(Integer id);

    List<Integer> getMenuRoleById(Integer id);

    void edit(Role role);

    void deleteRolePermissionById(Integer id);

    void deleteRoleMenuById(Integer id);

    void addRoleIdAndPermissionId(Map<String, Integer> map);

    void addRoleIdAndMenuId(Map<String, Integer> map);

    void add(Role role);
}
