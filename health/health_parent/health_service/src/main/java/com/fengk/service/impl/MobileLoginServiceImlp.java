package com.fengk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fengk.dao.MemberDao;
import com.fengk.pojo.Member;
import com.fengk.redis.RedisJob;
import com.fengk.service.MobileLoginService;
import com.fengk.utils.MessageConstant;
import com.fengk.utils.RedisMessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 *
 */
@Service(interfaceClass = MobileLoginService.class)
@Transactional
public class MobileLoginServiceImlp implements MobileLoginService {

    @Autowired
    MemberDao memberDao;
    @Autowired
    RedisJob redisJob;


    @Override
    public void check(Map map) {
        String telphone= (String) map.get("telephone");
        String validateCode= (String) map.get("validateCode");
        String codeFromRedis = redisJob.getCodeFromRedis(telphone, RedisMessageConstant.SENDTYPE_LOGIN);
        if (codeFromRedis==null){
            throw new RuntimeException("请先获取手机验证码再提交");
        }
        if (!codeFromRedis.equals(validateCode)){
            throw new RuntimeException(MessageConstant.VALIDATECODE_ERROR);

        }
        Member member = memberDao.findByTelephone(telphone);
        if (member==null){
member=new Member();
member.setPhoneNumber(telphone);
member.setRegTime(new Date());
memberDao.add(member);
        }
    }
}
