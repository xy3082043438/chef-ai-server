package com.lamb.chefaiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 添加库存请求体。
 */
@Data
@Schema(name = "InventoryAddRequest", description = "Add inventory request payload")
public class InventoryAddRequest {

    @JsonProperty("user_id")
    @Schema(description = "User ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    @JsonProperty("food_name")
    @Schema(description = "Food name", example = "Tomato", requiredMode = Schema.RequiredMode.REQUIRED)
    private String foodName;

    @Schema(description = "Quantity", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;
}
