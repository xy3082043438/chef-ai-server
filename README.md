# chef-ai-server

Spring Boot 4 服务端项目。

## 环境要求

- JDK 25
- MySQL 8.x
- Maven Wrapper（已包含 `mvnw` / `mvnw.cmd`）

## 配置

根据实际环境调整 `src/main/resources/application.yml` 中的数据库连接信息与 AI 接口配置，并设置以下环境变量：

- `DB_PASSWORD` - 数据库密码（默认：root）
- `XIAOMIMIMO_API_KEY` - 小米AI接口密钥
- `SILICONFLOW_API_KEY` - SiliconFlow接口密钥

## 运行

Windows:

```bat
mvnw.cmd spring-boot:run
```

macOS / Linux:

```bash
./mvnw spring-boot:run
```

## 如何将仓库改为公共的

如果你想将此仓库从私有改为公共，请按以下步骤操作：

### 方法一：通过 GitHub 网页界面

1. 打开仓库页面，进入 **Settings**（设置）
2. 滚动到页面底部，找到 **Danger Zone**（危险区域）
3. 点击 **Change repository visibility**（更改仓库可见性）
4. 选择 **Make public**（设为公共）
5. 输入仓库名称确认操作
6. 点击确认按钮

### 方法二：使用 GitHub CLI

如果你安装了 GitHub CLI (`gh`)，可以使用以下命令：

```bash
gh repo edit --visibility public
```

### 注意事项

⚠️ **在将仓库设为公共之前，请确保：**

1. **检查敏感信息**：确保代码中没有 API 密钥、密码、数据库凭据等敏感信息
2. **检查配置文件**：特别是 `application.yml`、`.env` 等配置文件
   - ✅ 本项目已将数据库密码改为使用环境变量 `DB_PASSWORD`
3. **检查提交历史**：敏感信息即使被删除，也可能存在于 Git 历史中
4. **使用环境变量**：本项目使用环境变量存储所有敏感配置，这是好的实践
5. **确认 LICENSE**：确保许可证（当前为 MIT License）符合你的预期

⚠️ **重要提醒**：Git 提交历史中可能包含之前的硬编码密码。如果需要完全清除历史中的敏感信息，请在公开前使用 `git filter-branch` 或 `BFG Repo-Cleaner` 清理历史记录。

## License

MIT License，见 `LICENSE.md`。
