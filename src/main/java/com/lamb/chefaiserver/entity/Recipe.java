package com.lamb.chefaiserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户菜谱记录。
 */
@Data
@TableName("menu")
public class Recipe {
    /** 主键。 */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /** 用户ID。 */
    private Integer userId;
    /** 菜谱名称。 */
    private String menuName;
    /** 食材（JSON 字符串）。 */
    private String ingredients;
    /** 步骤（JSON 字符串）。 */
    private String steps;
    /** 推荐说明。 */
    private String recommendation;
    /** 创建时间。 */
    private LocalDateTime createdAt;
    /** 更新时间。 */
    private LocalDateTime updatedAt;
}
