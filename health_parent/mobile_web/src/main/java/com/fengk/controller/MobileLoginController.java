package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.Result;
import com.fengk.service.MobileLoginService;
import com.fengk.utils.MessageConstant;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping(value = "/login")
public class MobileLoginController {
@Reference
MobileLoginService mobileLoginService;

    @RequestMapping(value = "/check")
    public Result check(@RequestBody Map map){
        try {


            mobileLoginService.check(map);

            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.LOGIN_fail);

        }
    }
}
