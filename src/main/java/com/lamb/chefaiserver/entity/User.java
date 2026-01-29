package com.lamb.chefaiserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户账号实体。
 */
@Data
@TableName("user")
public class User {
    /** 主键。 */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /** 用户名。 */
    private String username;
    /** 密码哈希。 */
    private String password;
    /** 邮箱。 */
    private String email;
    /** 创建时间。 */
    private LocalDateTime createdAt;
    /** 更新时间。 */
    private LocalDateTime updatedAt;
}
