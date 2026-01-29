package com.lamb.chefaiserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户登录请求体。
 */
@Data
@Schema(name = "UserLoginRequest", description = "User login request payload")
public class UserLoginRequest {

    @Schema(description = "Username", example = "tom", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Password", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
