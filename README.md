# INITSRC (启源)

**<center><font size=5 face="黑体">INITSRC（启源）</font></center>**

INITSRC是一款面向个人、中小企业快速开发的开源前后端管理项目。用户可以基于该项目进行网站管理后台、商城、OA等开发和学习。该项目后台基于Springboot+Mybaits-plus+Shiro+Jwt等技术来实现；前端基于Vue+Router+Vuex+Axios等技术来实现。

# 项目演示

- 项目地址：http://admin.initsrc.com
- 账号密码：initsrc/a123456
- 开发文档：http://docs.initsrc.com

# 联系我们

QQ技术交流群： 298264032

# 备注

承接各类软件开发，小程序、公众号、APP。有意向的联系微信：MISTAKEAI


# 技术介绍


服务端是基于Springboot、Shiro + JWT、Mybaits-plus + Pagehelper、Freemarker核心框架组成。

前端端是基于Vue、Router、Vuex、Element UI、Axios核心组件组成。

致力于协助初创科技企业建立技术储备、技术规范，让技术人员更加专注业务流，减少前期技术
搭建时间；此外，也帮助技术人员快速开发、减少重复工作。

## 应用环境

1. JDK 1.8
2. Apache Maven
3. Servlet 
4. MYSQL8.0
5. REDIS5.0

## 后端结构


```
initsrc    
├── initsrc-admin             // 后台管理端服务
│       └── module                         // 接口模块
│             └── controller               // 控制层
│             └── dao                      // 接口层
│             └── entity                   // 实体层
│             └── service                  // 实现层
├── initsrc-base              // 项目启动、properties总配置
├── initsrc-common            // 工具类
│       └── annotation                    // 自定义注解
│       └── base                          // 底层实体类
│       └── constant                      // 通用常量
│       └── controller                    // 工具类接口
│       └── enums                         // 通用枚举
│       └── exception                     // 通用异常
│       └── plugin                        // 第三方插件（redis、OSS）
│       └── util                          // 通用类处理
├── core                     // 框架核心
│       └── aspects                       // 注解实现
│       └── biz                           // 系统业务层
│       └── filter                        // 系统过滤层
│       └── module                        // 系统依赖模块（shiro、mybaits-plus）
├── initsrc-devtool         // 开发工具（不用可移除）
├── initsrc-monitor         // 系统监控（不用可移除）
├── initsrc-xxxxxx          // 其他模块
```

## 前端结构


```
initsrc-web    
├── src           
│    └── api                         // axios接口封装
│    └── assets                      // js、img、css
│    └── components                  // 自定义组件
│    └── layout                      // 页面布局
│    └── plugins                     // 第三方组件、自定义组件注册
│    └── router                      // 路由控制
│    └── store                       // vuex临时存储控制
│    └── views                       // 业务页面管理
```


## 后端技术选型

技术 | 版本 | 说明
---|---|---
Spring Boot	 | 2.3.0.RELEASE | 容器+MVC框架
Shiro | 1.4.0  | 认证和授权框架
JWT | 3.3.0 | 无状态认证协议
MyBatis-plus | 3.3.2 | ORM框架
Pagehelper | 5.1.10  | 分页插件
Freemarker | 2.3.28  | 代码生成引擎
Springfox-Swagger2	 | 2.9.2 | API文档管理
Redis | 5.0 | 分布式缓存
Druid | 1.1.10 | 数据库连接池
Lombok | 1.18.6	| 简化对象封装工具
Oshi-Core | 3.9.1 | 获取应用服务信息
P6spy | 3.8.0   | 针对数据库访问操作的动态监测框架


## 前端技术选型

技术 | 版本 | 说明
---|---|---
Vue	 | 2.6.11 | 一套用于构建用户界面的渐进式框架
vue-router | 3.2.0  | 路由管理器
vuex | 3.4.0 | 状态管理模式
Element-ui | 2.14.1 | 前端UI库
Axios | 0.21.1 | 一个基于 promise 的 HTTP 库
vue-apexcharts | 1.6.0 | 统计视图库
Xterm | 4.12.0 | 终端模拟器
@riophae/vue-treeselect | 0.4.0 | 树形选择器

# 前端页面展示

![首页](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E9%A6%96%E9%A1%B5.jpg "首页")
![页面](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E9%A1%B5%E9%9D%A2.jpg "页面")
![详情](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E8%AF%A6%E6%83%85.jpg)
![详情2.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E8%AF%A6%E6%83%852.jpg "页面1")
![编辑.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%BC%96%E8%BE%91.jpg "页面2")
![编辑2.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%BC%96%E8%BE%912.jpg "页面3")
![删除.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E5%88%A0%E9%99%A4.jpg "页面")
![1622744179(1).jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/1622744179%281%29.jpg "页面4")
![服务监控.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E6%9C%8D%E5%8A%A1%E7%9B%91%E6%8E%A7.jpg "页面5")
![缓存监控.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%BC%93%E5%AD%98%E7%9B%91%E6%8E%A7.jpg "页面6")
![druid.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/druid.jpg "页面7")
![api.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/api.jpg "页面8")
![pad端.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/pad%E7%AB%AF.jpg "页面9")
![pad端2.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/pad%E7%AB%AF2.jpg "页面12")
![暗黑模式.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E6%9A%97%E9%BB%91%E6%A8%A1%E5%BC%8F.jpg "页面11")
![暗黑模式2.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E6%9A%97%E9%BB%91%E6%A8%A1%E5%BC%8F2.jpg "页面12")
![暗黑模式3.jpg](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E6%9A%97%E9%BB%91%E6%A8%A1%E5%BC%8F3.jpg "页面13")
![输入图片说明](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%A7%BB%E5%8A%A8%E7%AB%AF.jpg "在这里输入图片标题")
![输入图片说明](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%A7%BB%E5%8A%A8%E7%AB%AF2.jpg "在这里输入图片标题")
![输入图片说明](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%A7%BB%E5%8A%A8%E7%AB%AF3.jpg "在这里输入图片标题")
![输入图片说明](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%A7%BB%E5%8A%A8%E7%AB%AF4.jpg "在这里输入图片标题")
![输入图片说明](https://initsrc.oss-cn-hangzhou.aliyuncs.com/%E7%A7%BB%E5%8A%A8%E7%AB%AF5.jpg "在这里输入图片标题")
