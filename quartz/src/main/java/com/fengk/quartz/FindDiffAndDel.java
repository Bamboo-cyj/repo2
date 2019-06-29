package com.fengk.quartz;

import com.fengk.redis.RedisJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class FindDiffAndDel {
    @Autowired
    RedisJob redisJob;


    public void findDiffAndDel(){
redisJob.findDiffAndDel();
    }


}
