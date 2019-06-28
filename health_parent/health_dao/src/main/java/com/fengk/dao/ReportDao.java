package com.fengk.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    Integer getCount(String month);

    List<Map> getSetmealReport();

    List<Map> getHotSetMeal();
}
