# 微服务基础框架

***
### 项目说明
本项目仅是用于复习微服务知识，并不代表能够真正运行

***
## 工程结构
```
project-parent
├── api-starter -- 子模块间的通信接口
├── common-starter -- 基础实体对象和工具类
├── gateway-service -- 网关服务
├── modules -- 自定义服务
├    ├── system-service -- 基础服务
├    ├── file-service -- 文件服务
├── packages -- 组件库
├    ├── rock-elasticsearch elasticsearch组件
├    ├── rock-log 日志组件
├    ├── rock-mybatis 数据库组件
├    ├── rock-rabbitMQ rabbitMQ组件
├    ├── rock-redis 缓存组件
├    ├── rock-security 安全组件
├    ├── rock-web 网络组件
└── pom.xml -- 工程 Maven 顶级依赖，统一控制版本和依赖
```
