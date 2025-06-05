package com.ghost.support.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ghost.common.utils.GhostResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    /**
     * 用户登录接口
     */
    @PostMapping("/login")
    public GhostResult login(@RequestParam String username, @RequestParam String password) {
        // 1. 验证账号密码（需自行实现）
        if (!checkUser(username, password)) {
            return GhostResult.success("账号或密码错误");
        }

        // 2. 登录成功，生成 Token
        StpUtil.login(10001); // 假设用户ID为10001
        return GhostResult.success(StpUtil.getTokenValue());
    }

    /**
     * 模拟账号密码验证
     */
    private boolean checkUser(String username, String password) {
        // 实际开发中应查询数据库
        return "admin".equals(username) && "123456".equals(password);
    }
}