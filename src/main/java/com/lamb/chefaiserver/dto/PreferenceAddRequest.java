package com.lamb.chefaiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 新增偏好请求体。
 */
@Data
@Schema(name = "PreferenceAddRequest", description = "Add preference request payload")
public class PreferenceAddRequest {

    @JsonProperty("user_id")
    @Schema(description = "User ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    @JsonProperty("preference_name")
    @Schema(description = "Preference name", example = "Less spicy", requiredMode = Schema.RequiredMode.REQUIRED)
    private String preferenceName;
}
