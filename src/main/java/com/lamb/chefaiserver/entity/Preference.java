package com.lamb.chefaiserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户偏好记录。
 */
@Data
@TableName("user_preference")
public class Preference {
    /** 主键。 */
    @TableId(value = "preference_id", type = IdType.AUTO)
    private Integer preferenceId;

    /** 用户ID。 */
    private Integer userId;
    /** 偏好名称。 */
    private String preferenceName;
    /** 创建时间。 */
    private LocalDateTime createdAt;
    /** 更新时间。 */
    private LocalDateTime updatedAt;
}
