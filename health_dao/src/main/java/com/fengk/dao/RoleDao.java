package com.fengk.dao;

import com.fengk.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRoles(Integer id);
}
