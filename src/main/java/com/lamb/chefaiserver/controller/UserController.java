package com.lamb.chefaiserver.controller;

import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.dto.UserLoginRequest;
import com.lamb.chefaiserver.dto.UserRegisterRequest;
import com.lamb.chefaiserver.entity.User;
import com.lamb.chefaiserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户注册、登录与资料查询接口。
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /** 用户服务。 */
    @Autowired
    private UserService userService;

    /**
     * 注册新用户。
     *
     * @param request 请求体
     * @return 新用户ID
     */
    @PostMapping("/register")
    public Result<Integer> register(@RequestBody UserRegisterRequest request) {
        return userService.register(
                request.getUsername(),
                request.getPassword(),
                request.getEmail()
        );
    }

    /**
     * 用户登录并返回会话信息。
     *
     * @param request 请求体
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody UserLoginRequest request) {
        return userService.login(
                request.getUsername(),
                request.getPassword()
        );
    }

    /**
     * 根据用户ID获取资料。
     *
     * @param userId 用户ID
     * @return 用户资料
     */
    @GetMapping("/info/{userId}")
    public Result<User> getUserInfo(@PathVariable Integer userId) {
        return userService.getUserInfo(userId);
    }
}
