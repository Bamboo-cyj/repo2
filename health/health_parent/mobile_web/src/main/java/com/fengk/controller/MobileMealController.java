package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.Result;

import com.fengk.pojo.Setmeal;
import com.fengk.service.MobileMealService;
import com.fengk.utils.MessageConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/setmeal")
public class MobileMealController {
  @Reference
  MobileMealService mobileMealService;
    @RequestMapping(value = "/getSetmeal")
    public Result getSetmeal(){
        try {
          List<Setmeal> list= mobileMealService.getSetmeal();
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,list);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

        }
    }
    @RequestMapping(value = "/findById")
    public Result findById(Integer id){
        try {


            Setmeal setmeal= mobileMealService.findById(id);
            System.out.println("--------"+setmeal);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);

        }
    }
}
