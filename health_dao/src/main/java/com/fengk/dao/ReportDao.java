package com.fengk.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    Integer getCount(String month);

    List<Map> getSetmealReport();

    List<Map> getHotSetMeal();

    List<Map> getMemberSexReport();

    Integer getAgeBetween(@Param("ageBegin") String ageBegin, @Param("ageEnd") String ageEnd);

    Integer getAgeNull();
}
