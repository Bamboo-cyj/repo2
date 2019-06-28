package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.HealthCheckDao;
import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;
import com.fengk.pojo.CheckItem;
import com.fengk.pojo.HealthOrderTest;
import com.fengk.service.HealthCheckService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 */
@Service(loadbalance = "random",interfaceClass = HealthCheckService.class)
public class HealthCheckServiceImpl implements HealthCheckService {
    @Autowired
    HealthCheckDao healthCheckDao;

    @Override
    public int save(HealthOrderTest healthOrderTest) {
        return healthCheckDao.save(healthOrderTest);
    }

    @Override
    public List<HealthOrderTest> findAll() {
        System.out.println("进入了service");
        return healthCheckDao.findAll();

    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
       /* String queryString ="%"+queryPageBean.getQueryString()+"%";*/
        String queryString=queryPageBean.getQueryString();
        if (queryString!=null){
            queryString="%"+queryString+"%";
        }
Page<CheckItem> page= healthCheckDao.findPageByCondition(queryString);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void add(CheckItem checkItem) {
        healthCheckDao.add(checkItem);
    }

    @Override
    public void delete(Integer id) {
       Integer idIsInCheckGroup = healthCheckDao.findIdIsInCheckGroup(id);
       if (idIsInCheckGroup>0){
           throw new RuntimeException("该检查项目在检查项目中,无法删除");
       }else {
           healthCheckDao.delete(id);
       }
    }

    @Override
    public CheckItem findById(Integer id) {
        return healthCheckDao.findById(id);
    }

    @Override
    public void edit(CheckItem checkItem) {
        healthCheckDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAllItems() {
        return healthCheckDao.findAllItems();
    }
}
