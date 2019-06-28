package com.fengk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fengk.entity.Result;


import com.fengk.service.MobileValidateCodeService;
import com.fengk.utils.MessageConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping(value = "/validateCode")
@RestController
public class MobileValidateCodeController {

@Reference
MobileValidateCodeService mobileValidateCodeService;

    @RequestMapping(value = "/send4Order")
    public Result send4Order(String telephone){
        try {


           mobileValidateCodeService.send4Order(telephone);

            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);

        }
    }

    @RequestMapping(value = "/send4Login")
    public Result send4Login(String telephone){
        try {


            mobileValidateCodeService.send4Login(telephone);

            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        }catch (RuntimeException runtimeException){
            runtimeException.printStackTrace();
            return new Result(false,runtimeException.getMessage());
        }
        catch(Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);

        }
    }


}
