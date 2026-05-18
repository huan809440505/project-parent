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
├    ├── order-service -- 订单服务
├    ├── order-service -- 商品服务
├    ├── order-service -- 用户服务
└── pom.xml -- 工程 Maven 顶级依赖，统一控制版本和依赖
```
