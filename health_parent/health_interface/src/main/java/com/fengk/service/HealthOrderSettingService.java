package com.fengk.service;

import com.fengk.pojo.OrderSetting;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 */
public interface HealthOrderSettingService {
    void upload(OrderSetting orderSetting);

    List<OrderSetting> findAllOrder(String currentMonth);
}
