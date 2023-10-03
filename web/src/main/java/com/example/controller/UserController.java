package com.example.controller;

import com.example.common.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 普通用户接口
 * @Author: suragi
 * @Date: 2023/8/14 23:13
 * @Description:
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping("/get")
    public Result<String> get(Principal principal){
        return Result.ok("Hello user " + principal.getName());
    }

    @PostMapping("/post")
    public Result<String> post(String msg){
        return Result.ok(StringUtils.isBlank(msg) ? "user post method" : msg);
    }
}

