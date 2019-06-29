package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.HealthRoleDao;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.Menu;
import com.fengk.pojo.Permission;
import com.fengk.pojo.Role;
import com.fengk.service.HealthRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = HealthRoleService.class)
@Transactional
public class HealthRoleServiceImpl implements HealthRoleService {

    @Autowired
    private HealthRoleDao healthRoleDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        /* String queryString ="%"+queryPageBean.getQueryString()+"%";*/
        String queryString=queryPageBean.getQueryString();
        if (queryString!=null){
            queryString="%"+queryString+"%";
        }
        Page<Role> page= healthRoleDao.findPageByCondition(queryString);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void delete(Integer id) {
        healthRoleDao.deleteRoleMenuById(id);
        healthRoleDao.deleteRolePermissionById(id);
        healthRoleDao.delete(id);

    }

    @Override
    public Role findById(Integer id) {
        return healthRoleDao.findById(id);
    }

    @Override
    public List<Integer> getPermissionRoleById(Integer id) {
        return healthRoleDao.getPermissionRoleById(id);
    }

    @Override
    public List<Integer> getMenuRoleById(Integer id) {
        return healthRoleDao.getMenuRoleById(id);
    }


    @Override
    public void edit(Role role, Integer[] permissionIds, Integer[] menuIds) {
        healthRoleDao.edit(role);
        healthRoleDao.deleteRolePermissionById(role.getId());
        healthRoleDao.deleteRoleMenuById(role.getId());

        if (permissionIds != null) {
            for (Integer permissionId : permissionIds) {
                Map<String, Integer> map = new HashMap();
                map.put("roleId", role.getId());
                map.put("permissionId", permissionId);
                healthRoleDao.addRoleIdAndPermissionId(map);
            }
        }

        if (menuIds != null) {
            for (Integer menuId : menuIds) {
                Map<String, Integer> map = new HashMap();
                map.put("roleId", role.getId());
                map.put("menuId", menuId);
                healthRoleDao.addRoleIdAndMenuId(map);
            }
        }
    }

    @Override
    public void add(Role role, Integer[] permissionIds, Integer[] menuIds) {
        healthRoleDao.add(role);

        if (permissionIds != null) {
            for (Integer permissionId : permissionIds) {
                Map<String, Integer> map = new HashMap();
                map.put("roleId", role.getId());
                map.put("permissionId", permissionId);
                healthRoleDao.addRoleIdAndPermissionId(map);
            }
        }

        if (menuIds != null) {
            for (Integer menuId : menuIds) {
                Map<String, Integer> map = new HashMap();
                map.put("roleId", role.getId());
                map.put("menuId", menuId);
                healthRoleDao.addRoleIdAndMenuId(map);
            }
        }
    }

}
