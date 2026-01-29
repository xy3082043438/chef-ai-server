package com.lamb.chefaiserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lamb.chefaiserver.common.Result;
import com.lamb.chefaiserver.entity.User;
import com.lamb.chefaiserver.mapper.UserMapper;
import com.lamb.chefaiserver.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户服务实现（基于 MyBatis-Plus）。
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /** {@inheritDoc} */
    @Override
    public Result<Integer> register(String username, String password, String email) {
        // 检查邮箱是否已存在
        long count = this.count(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
        if (count > 0) {
            return Result.error("邮箱已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        // Simple MD5 hash
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        
        boolean success = this.save(user);
        if (success) {
            return Result.success(user.getUserId());
        } else {
            return Result.error("注册失败");
        }
    }

    /** {@inheritDoc} */
    @Override
    public Result<Map<String, String>> login(String username, String password) {
        String hashedPassword = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .eq(User::getPassword, hashedPassword));

        if (user != null) {
            Map<String, String> data = new HashMap<>();
            String token = UUID.randomUUID().toString().replace("-", "");
            data.put("token", token);
            data.put("userId", String.valueOf(user.getUserId()));
            return Result.success(data);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    /** {@inheritDoc} */
    @Override
    public Result<User> getUserInfo(Integer userId) {
        User user = this.getById(userId);
        if (user != null) {
            user.setPassword(null); // Hide password
            return Result.success(user);
        } else {
            return Result.error("用户不存在");
        }
    }
}
