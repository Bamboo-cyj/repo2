package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.fengk.dao.MobileMealDao;
import com.fengk.pojo.Setmeal;
import com.fengk.redis.RedisJob;
import com.fengk.service.MobileMealService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 */
@Service(interfaceClass = MobileMealService.class)
public class MobileMealServiceImpl implements MobileMealService {

    @Autowired
    MobileMealDao mobileMealDao;

    @Autowired
    RedisJob redisJob;


    @Override
    public List<Setmeal> getSetmeal() {
        String redisMealName = "allMeal";
        List<Setmeal> setmealList = null;
        Object allMeal = redisJob.getSetmeal(redisMealName);
        if (allMeal == null) {
            setmealList = mobileMealDao.getSetmeal();

            redisJob.add(redisMealName,JSON.toJSONString(setmealList));
        } else {
            setmealList = JSON.parseArray((String) allMeal, Setmeal.class);
        }
        return setmealList;
    }

    @Override
    public Setmeal findById(Integer id) {

        Setmeal mobileMealDaoById = mobileMealDao.findById(id);

        System.out.println(mobileMealDaoById);


        return mobileMealDaoById;
    }
}
