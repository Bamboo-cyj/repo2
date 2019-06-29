package com.fengk.service;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.Menu;
import com.fengk.pojo.Permission;
import com.fengk.pojo.Role;

import java.util.List;

public interface HealthRoleService {
    PageResult findPage(QueryPageBean queryPageBean);

    void delete(Integer id);

    Role findById(Integer id);

    List<Integer> getPermissionRoleById(Integer id);

    List<Integer> getMenuRoleById(Integer id);

    void edit(Role role, Integer[] permissionIds, Integer[] menuIds);

    void add(Role role, Integer[] permissionIds, Integer[] menuIds);
}
