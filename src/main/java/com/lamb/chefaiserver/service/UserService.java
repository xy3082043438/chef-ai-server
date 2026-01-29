package com.lamb.chefaiserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lamb.chefaiserver.entity.User;
import com.lamb.chefaiserver.common.Result;
import java.util.Map;

/**
 * 用户领域服务。
 */
public interface UserService extends IService<User> {
    /**
     * 注册新用户。
     *
     * @param username 用户名
     * @param password 原始密码
     * @param email 邮箱
     * @return 新用户ID
     */
    Result<Integer> register(String username, String password, String email);

    /**
     * 用户登录并返回结果。
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    Result<Map<String, String>> login(String username, String password);

    /**
     * 根据用户ID获取资料。
     *
     * @param userId 用户ID
     * @return 用户资料
     */
    Result<User> getUserInfo(Integer userId);
}
