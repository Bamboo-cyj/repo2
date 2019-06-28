package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.Result;
import com.fengk.pojo.Order;

import com.fengk.service.MobileOrderService;
import com.fengk.utils.MessageConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 */
@RequestMapping(value = "/order")
@RestController
public class MobileOrderController {

@Reference
MobileOrderService mobileOrderService;

    @RequestMapping(value = "/submit")
    public Result submit(@RequestBody Map orderInfo){
        try {


         Order order= mobileOrderService.submit(orderInfo);

            return new Result(true, MessageConstant.ORDER_SUCCESS,order);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ORDER_FULL);

        }
    }
    @RequestMapping(value = "/findById")
    public Result findById(Integer id){
        try {


            Map map= mobileOrderService.findById(id);

            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);

        }
    }
}
