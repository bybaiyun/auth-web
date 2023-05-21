package com.example.demo.controller;

import com.example.demo.service.DemoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class DemoController {
    @Resource
    private DemoService demoService;

    @RequestMapping("/healthCheck")
    public String healthCheck(){
        log.info("应用启动成功！");
        return "success!";
    }

    @GetMapping("/dbtest")
    public String dbTest(){
        return demoService.test();
    }
}
