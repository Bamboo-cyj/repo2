package com.fengk.service;

import com.fengk.pojo.Setmeal;

import java.util.List;

public interface MobileMealService {
    List<Setmeal> getSetmeal();

    Setmeal findById(Integer id);
}
