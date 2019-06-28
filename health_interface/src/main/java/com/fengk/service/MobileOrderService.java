package com.fengk.service;

import com.fengk.pojo.Order;

import java.util.Map;

public interface MobileOrderService {
    Order submit(Map orderInfo);

    Map findById(Integer id);
}
