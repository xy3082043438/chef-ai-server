# chef-ai-server

「智能菜谱助手」后端服务（Spring Boot 4）。为前端提供用户体系、偏好/库存管理、菜谱推荐与详情等 API。

## 环境要求

- JDK 25
- MySQL 8.x
- Maven Wrapper（已包含 `mvnw` / `mvnw.cmd`）

## 配置

根据实际环境调整 `src/main/resources/application.yml` 中的数据库连接信息与 AI 接口配置，并设置以下环境变量：

- `XIAOMIMIMO_API_KEY`
- `SILICONFLOW_API_KEY`

## 运行

Windows:

```bat
mvnw.cmd spring-boot:run
```

macOS / Linux:

```bash
./mvnw spring-boot:run
```

## 许可证

MIT License，见 `LICENSE.md`。
