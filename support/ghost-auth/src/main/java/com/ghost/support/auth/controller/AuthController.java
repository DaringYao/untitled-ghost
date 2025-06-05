package com.ghost.support.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    /**
     * 受保护的接口（需登录后访问）
     */
    @GetMapping("/getUserInfo")
    public String getUserInfo() {
        // 校验登录状态（未登录则抛出 NotLoginException）
        StpUtil.checkLogin();

        // 获取当前登录用户ID
        Object userId = StpUtil.getLoginId();
        return "当前登录用户ID: " + userId;
    }
}