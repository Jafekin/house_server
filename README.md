<!--
 * @Author        Jafekin 1946847867@qq.com
 * @Date          2025-12-08 21:36:17
 * @LastEditTime  2025-12-08 21:41:36
 * @Description   
 * 
-->
# House Server

> Java高级编程与应用大作业
> 
Spring Boot 租房管理服务，提供房源、用户、预约、缴费与工单等 REST 接口。项目结合 MyBatis + Flyway、JWT 工具类与 Druid 数据源，保持架构轻量且易于上线部署。

## 功能亮点
- **房源全流程**：`HouseController`、`HouseListService` 提供 CRUD 与 PageHelper 分页能力。
- **用户与认证**：`UserController` 管理注册/登录，`JwtUtil` 提供令牌生成与解析，可接入安全过滤器。
- **预约与缴费**：`ScheduleController`、`PaidController` 负责看房预约与费用流程。
- **问题处理**：`SolveController` 用于售后/维修工单闭环。
- **数据库版本**：Flyway 启动时自动执行 `src/main/resources/db/migration` 下的 SQL，保证多环境一致。

## 技术栈
- Java 8+
- Spring Boot 2.0.1（WAR 打包，可直接以 Spring Boot 运行）
- MyBatis + PageHelper
- MySQL 8.x + Flyway
- Spring Security（当前放开所有接口，便于后续加固）
- JWT（io.jsonwebtoken）工具类
- Alibaba Druid 连接池

## 项目结构
```
src
 └─ main
    ├─ java/com/house
    │  ├─ config        # 数据源与安全配置
    │  ├─ controller    # 房源/用户/预约/缴费/工单 REST 控制器
    │  ├─ dao           # Mapper 接口，配合 @MapperScan
    │  ├─ service       # 业务逻辑层
    │  ├─ dto/vo        # 参数与视图对象
    │  ├─ utils         # 工具类（JwtUtil、DateUtil 等）
    │  └─ common        # 统一返回体 Result/StatusCode
    └─ resources
       ├─ application.yml  # 端口、数据源、MyBatis 配置
       ├─ mapper/*.xml     # MyBatis SQL 映射
       └─ db/migration     # Flyway 迁移脚本（change.sql、db.sql）
```

## 快速开始
### 环境依赖
- JDK 8 或 11（`java -version`）
- Maven 3.6+
- MySQL 8.0+

### 配置数据源
编辑 `src/main/resources/application.yml`，或在启动前通过环境变量覆盖：
```
set SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
set SPRING_DATASOURCE_USERNAME=root
set SPRING_DATASOURCE_PASSWORD=secret
```
当前 `spring.flyway.*` 已开启，启动时会自动建库并迁移 `db` 模式。

### 构建与运行
```
mvn clean package
java -jar target/house_zzq-1.0-SNAPSHOT.war
```
默认监听 `http://localhost:9002`。开发期也可直接使用：
```
mvn spring-boot:run
```

### 手动迁移数据库
本地需要重置迁移时：
```
mvn -Dflyway.cleanDisabled=false flyway:clean flyway:migrate
```
**注意**：`flyway:clean` 会清空库表，仅限测试环境使用。

## 安全 & JWT
- `WebSecurityConfig` 暂时放行所有请求，后续可将 `.antMatchers("/**").permitAll()` 替换为更细粒度规则。
- `JwtUtil` 默认密钥 `ititit`、过期时间 1 小时，建议通过配置或环境变量外置。

## 测试
运行默认测试：
```
mvn test
```

## 常见问题
- **无法连接 MySQL**：检查 `application.yml` 中的主机/端口，并确保数据库账号有 DDL 权限，便于 Flyway 初始化。
- **端口被占用**：修改 `application.yml` 的 `server.port`，或启动时添加 `-Dserver.port=9090`。
- **编码/时区报警**：保留 JDBC URL 中的 `useUnicode=true`、`characterEncoding=utf8`、`serverTimezone=UTC` 参数。

## 后续计划
- 使用 Spring Security 过滤器校验 `Authorization: Bearer <JWT>`，完善鉴权。
- 引入 MockMvc 等测试覆盖控制层与服务层。
- 编写多阶段 Dockerfile，便于容器化交付。
