package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.aliyuncs.exceptions.ClientException;
import com.fengk.redis.RedisJob;

import com.fengk.service.MobileValidateCodeService;
import com.fengk.utils.SMSUtils;
import com.fengk.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service(interfaceClass = MobileValidateCodeService.class)
@Transactional
public class MobileValidateCodeServiceImpl implements MobileValidateCodeService {

    @Autowired
    RedisJob redisJob;
    @Override
    public void send4Order(String telephone) {
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        System.out.println(validateCode);
        try {
            SMSUtils.sendShortMessage("SMS_165690906",telephone,validateCode.toString());
            redisJob.addCode(60*60*24,telephone,validateCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send4Login(String telephone) {
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        System.out.println(validateCode);
        try {
            SMSUtils.sendShortMessage("SMS_165690906",telephone,validateCode.toString());
            redisJob.addLoginCode(60*60*24,telephone,validateCode);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
