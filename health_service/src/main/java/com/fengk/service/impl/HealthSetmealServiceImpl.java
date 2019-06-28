package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.HealthSetmealDao;

import com.fengk.entity.PageResult;
import com.fengk.entity.QueryPageBean;



import com.fengk.pojo.Setmeal;

import com.fengk.redis.RedisJob;
import com.fengk.service.HealthSetmealService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

import java.util.Map;

/**
 *
 */
@Service(loadbalance = "random",interfaceClass = HealthSetmealService.class)
public class HealthSetmealServiceImpl implements HealthSetmealService {

    @Autowired
    HealthSetmealDao healthSetmealDao;

    @Autowired
    RedisJob redisJob;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        String queryString=queryPageBean.getQueryString();
        if (queryString!=null){
            queryString="%"+queryString+"%";
        }
        Page<Setmeal> page= healthSetmealDao.findPageByCondition(queryString);
        PageResult pageResult=new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        healthSetmealDao.add(setmeal);

        if (checkgroupIds!=null){
            for (Integer checkgroupId : checkgroupIds) {
                Map<String,Integer> map=new HashMap();
                map.put("mealId",setmeal.getId());
                map.put("groupId",checkgroupId);
                healthSetmealDao.addMealIdAndGroupId(map);
            }
        }
    }

    @Override
    public void addDatabaseRedis(String img) {
        redisJob.redisInDatabase(img);
    }

    @Override
    public void addWebRedis(String filename) {
redisJob.redisOnWeb(filename);
    }


}
