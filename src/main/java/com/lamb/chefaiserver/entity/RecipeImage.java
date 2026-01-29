package com.lamb.chefaiserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 菜谱图片信息。
 */
@Data
@TableName("menu_image")
public class RecipeImage {
    /** 主键。 */
    @TableId(value = "image_id", type = IdType.AUTO)
    private Integer imageId;

    /** 菜谱ID。 */
    private Integer menuId;
    /** 图片地址。 */
    private String imageUrl;
    /** 创建时间。 */
    private LocalDateTime createdAt;
    /** 更新时间。 */
    private LocalDateTime updatedAt;
}
