package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.HealthOrderSettingDao;
import com.fengk.pojo.OrderSetting;
import com.fengk.service.HealthOrderSettingService;
import com.fengk.utils.POIUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service(interfaceClass = HealthOrderSettingService.class)
public class HealthOrderSettingServiceImpl implements HealthOrderSettingService {
    @Autowired
    HealthOrderSettingDao healthOrderSettingDao;
    @Override
    public void upload(OrderSetting orderSetting) {
        /*List<String[]> readExcel = null;
        try {
            readExcel = POIUtils.readExcel(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("dd/MM/yy");
        for (String[] orderSetingExcel : readExcel) {
            OrderSetting orderSetting=new OrderSetting();
            try {
                orderSetting.setOrderDate(simpleDateFormat.parse(orderSetingExcel[0]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            orderSetting.setNumber(Integer.parseInt(orderSetingExcel[1]));
            update(orderSetting);
        }*/


        update(orderSetting);
    }

    @Override
    public List<OrderSetting> findAllOrder(String currentMonth) {


        System.out.println("进入了------"+currentMonth);
        currentMonth=currentMonth+"%";
        List<OrderSetting> orderSettings=  healthOrderSettingDao.findAll(currentMonth);
        return orderSettings;
    }
    public void update(OrderSetting orderSetting){
        SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
       String stringDate=simpleDateFormat.format(orderSetting.getOrderDate());
       int orderNumber=orderSetting.getNumber();
        Map<String,Object> map=new HashMap<>();
        map.put("orderDate",stringDate);
        map.put("number",orderNumber);
        map.put("reservations", orderSetting.getReservations());
        long findLength=healthOrderSettingDao.findLength(stringDate);
        if (findLength==0){
            healthOrderSettingDao.add(map);
        }else {

            healthOrderSettingDao.update(map);
        }
    }
}
