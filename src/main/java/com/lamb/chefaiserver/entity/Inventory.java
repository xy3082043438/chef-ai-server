package com.lamb.chefaiserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户库存项。
 */
@Data
@TableName("user_inventory")
public class Inventory {
    /** 主键。 */
    @TableId(value = "inventory_id", type = IdType.AUTO)
    private Integer inventoryId;

    /** 用户ID。 */
    private Integer userId;
    /** 食材名称。 */
    private String foodName;
    /** 数量。 */
    private Integer quantity;
    /** 创建时间。 */
    private LocalDateTime createdAt;
    /** 更新时间。 */
    private LocalDateTime updatedAt;
}
