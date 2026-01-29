package com.lamb.chefaiserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户注册请求体。
 */
@Data
@Schema(name = "UserRegisterRequest", description = "User register request payload")
public class UserRegisterRequest {

    @Schema(description = "Username", example = "tom", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @Schema(description = "Password", example = "123456", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @Schema(description = "Email", example = "tom@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
