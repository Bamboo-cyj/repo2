package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.Result;

import com.fengk.pojo.OrderSetting;
import com.fengk.service.HealthOrderSettingService;
import com.fengk.utils.ExcelUtil.ExcelLogs;
import com.fengk.utils.ExcelUtil.ExcelUtil;
import com.fengk.utils.MessageConstant;

import com.fengk.utils.POIUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
@RestController
@RequestMapping(value = "/ordersetting")
public class HealthOrderSettingController {

    @Reference
    HealthOrderSettingService healthOrderSettingService;

    @RequestMapping(value = "/editNumberByDate")

    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        System.out.println(orderSetting+"获取的");
        try {
healthOrderSettingService.upload(orderSetting);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }

    }

    @RequestMapping(value = "/upload")

    public Result upload(@RequestParam("excelFile")MultipartFile excelFile) {
        System.out.println(excelFile.getOriginalFilename());
        try {

            uploadFile(excelFile);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }

    }

    @RequestMapping(value = "/findAllOrder")
    public Result findAllOrder(String currentMonth) {
        try {
            System.out.println(currentMonth);
            List<OrderSetting> orderList= healthOrderSettingService.findAllOrder(currentMonth);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,orderList);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }

    }

    public void uploadFile(MultipartFile excelFile){

        List<String[]> readExcel = null;
        try {
           /* InputStream inputStream = excelFile.getInputStream();
            ExcelLogs logs=new ExcelLogs();
            Collection<Map> importExcel= ExcelUtil.importExcel(Map.class, inputStream, "yyyy-MM-dd", logs , 0);
            for (Map map : importExcel) {
                System.out.println(map);
            }*/
            readExcel = POIUtils.readExcel(excelFile);
            System.out.println("-------------------"+readExcel+"-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (readExcel!=null && readExcel.size()>0){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
            for (String[] orderSetingExcel : readExcel) {
                OrderSetting orderSetting= null;
                try {

                    orderSetting = new OrderSetting();

                    orderSetting.setOrderDate(simpleDateFormat.parse(orderSetingExcel[0]));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                orderSetting.setNumber(Integer.parseInt(orderSetingExcel[1]));
                System.out.println(orderSetting+"ordersetting值");
                healthOrderSettingService.upload(orderSetting);
            }
        }



    }
}
