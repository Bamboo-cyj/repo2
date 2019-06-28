package com.fengk.dao;

import com.fengk.pojo.Setmeal;

import java.util.List;

public interface MobileMealDao {
    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
