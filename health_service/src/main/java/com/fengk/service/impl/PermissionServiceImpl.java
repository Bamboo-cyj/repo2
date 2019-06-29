package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.PermissionDao;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.Permission;
import com.fengk.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = PermissionService.class)
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        /* String queryString ="%"+queryPageBean.getQueryString()+"%";*/
        String queryString=queryPageBean.getQueryString();
        if (queryString!=null){
            queryString="%"+queryString+"%";
        }
        Page<Permission> page= permissionDao.findPageByCondition(queryString);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void add(Permission permission) {
        permissionDao.add(permission);
    }

    @Override
    public void delete(Integer id) {
        permissionDao.delete(id);
    }

    @Override
    public void edit(Permission permission) {
        permissionDao.edit(permission);
    }

    @Override
    public Permission findById(Integer id) {
        return permissionDao.findById(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }
}
