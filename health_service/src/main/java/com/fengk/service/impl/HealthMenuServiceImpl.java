package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.HealthMenuDao;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.CheckItem;
import com.fengk.pojo.Menu;
import com.fengk.service.HealthMenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = HealthMenuService.class)
@Transactional
public class HealthMenuServiceImpl implements HealthMenuService {

    @Autowired
    private HealthMenuDao healthMenuDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        /* String queryString ="%"+queryPageBean.getQueryString()+"%";*/
        String queryString=queryPageBean.getQueryString();
        if (queryString!=null){
            queryString="%"+queryString+"%";
        }
        Page<Menu> page= healthMenuDao.findPageByCondition(queryString);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void add(Menu menu) {
        healthMenuDao.add(menu);
    }

    @Override
    public void delete(Integer id) {
        healthMenuDao.delete(id);
    }

    @Override
    public void edit(Menu menu) {
        healthMenuDao.edit(menu);
    }

    @Override
    public Menu findById(Integer id) {
        return healthMenuDao.findById(id);
    }

    @Override
    public List<Menu> findAll() {
        return healthMenuDao.findAll();
    }


}
