package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class DemoApplication {

    /**
     * 为什么 demo 是 client?
     * 按照 oauth2.1 的授权码模式，最后拿到 accessToken 的是 client
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
