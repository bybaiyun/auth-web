package com.example.controller;

import com.example.common.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author: suragi
 * @Date: 2023/8/14 15:30
 * @Description:
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @GetMapping("/get")
    public Result get(Principal principal){
        return Result.ok("Hello " + principal.getName());
    }

    @PostMapping("/post")
    public Result post(String msg){
        return Result.ok(StringUtils.isBlank(msg) ? "admin post method" : msg);
    }
}

