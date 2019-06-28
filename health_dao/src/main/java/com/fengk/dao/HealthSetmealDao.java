package com.fengk.dao;


import com.fengk.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface HealthSetmealDao {
    Page<Setmeal> findPageByCondition(@Param("queryByString") String queryByString);

    void add(Setmeal setmeal);

    void addMealIdAndGroupId(Map<String, Integer> map);

   }
