package com.shop.controller;

import com.shop.pojo.LoginResult;
import com.shop.pojo.Result;
import com.shop.pojo.User;
import com.shop.service.LoginService;
import com.shop.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<LoginResult> login(@RequestParam("email") String email,
                        @RequestParam("password") String password) {
        User user = loginService.login(email, password);
        // 登录成功,生成令牌,下发令牌
        if (user != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", user.getEmail());
            claims.put("password", user.getPassword());
            String jwt = JwtUtil.generateJwt(claims); //jwt包含了当前登录的员工信息

            String userName = user.getEmail().split("@")[0];
            LoginResult loginResult = new LoginResult(userName, jwt);
            return Result.success(loginResult);
        }
        // 登录失败, 返回错误信息
        return Result.error("username or password ERROR");
    }

    @PostMapping("/login/password")
    public Result changePassword(@RequestParam("email") String email,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        User user = loginService.login(email, oldPassword);
        // email和oldPassword存在
        if (user != null) {
            loginService.changePassword(email, newPassword);
            return Result.success();
        } else {
            return Result.error("ERROR");
        }
    }
}
