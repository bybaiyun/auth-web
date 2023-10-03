package com.example.service.impl;


import com.example.service.DemoService;
import com.example.utils.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("demoService")
public class DemoServiceImpl implements DemoService {
    @Resource
    private RedisUtil redisUtil;


    @Override
    public String test() {
        if(redisUtil.tryLock("lockKey", 30, 3)){
            return "redis lock success";
        }
        //TransactionUtils.doAfterTransaction(new DoTransactionCompletion(() -> System.out.println("transaction proper committed")));
        return "redis lock failed";
    }
}
