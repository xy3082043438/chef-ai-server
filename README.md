# chef-ai-server

Spring Boot 4 服务端项目。

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

## License

MIT License，见 `LICENSE.md`。
