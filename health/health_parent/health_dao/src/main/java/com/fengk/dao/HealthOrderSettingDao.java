package com.fengk.dao;

import com.fengk.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HealthOrderSettingDao {
    long findLength(String date);

    void add(Map map);

    void update(Map map);

    List<OrderSetting> findAll(String currentMonth);

    OrderSetting findOrderSettingByDate(String oderDate);

    void addCount(String orderDate);
}
