##项目介绍
-----
这是为了探索Spring Security Oauth2在Spring Cloud的最佳实践而开发的微服务项目。

该微服务项目简单的分有以下基础服务
- authorization-server
- gateway
- feign-api
- sysadmin
- common
- reader-server
- articles-server

各服务的端口如下：

| 服务名                  | 端口    | 备注  |
|----------------------|-------|-----|
| authenticationserver | 7050  |     |
| authorizationserver  | 7051  |     |
| reader               | 8088  |     |
| articles             | 8090  |     |         
| sysadmin             | 9001  |     |
| feignapi             | 9050  |     |
| gateway              | 10010 |     |


##开发环境搭建

###技术栈
- |java 17
- spring-boot 3.2.3
- spring-cloud 2023.0.0
- mysql 8.0.28
- mybatis 3.5.15
- nacos 2023.0.0.0-RC1

搭建以下服务

| 服务      | 服务名   | 端口   | 备注                           |
|---------|-------|------|------------------------------|
| 数据库     | mysql | 3306 | 目前各应用共用1个实例，各应用可建不同的database |
| 注册与配置中心 | nacos | 8850 |                              |
| 反向代理    | nginx | 80   |



