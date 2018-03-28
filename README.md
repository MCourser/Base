# 介绍

这个项目分为`前台` 和`后台`两个部分，前台项目是由Angular2编写，后台项目由SpringBoot、JPA和Swagger编写，实现了基于权限、角色、用户的基本系统。

## 安装

### 依赖软件

`JDK8` `maven` `vagrant`或者`mysql` `nodejs` `angular-cli`

### 数据库

1. 本地没有`mysql`

   ```shell
   cd Base/env/dev/
   vagrant up
   ```

   vagrant启动后会在它管理的linux镜像中部署好`mysql`，数据库默认为`base`，密码为`123456`

2. 本地有数据库请跳过

### 导入后台项目

打开`Eclipse`，导入`Base/server`为`maven`项目。`Base/server`为`maven`父`pom`；`Base/server/service-web`为子项目，用来做Web服务。

### 导入前台

打开`WebStorm`，打开`Base/web`，然后在这个目录下使用`npm`安装依赖库。

### 运行后台

打开`application.yml`，确认数据库配置信息，并确认与本地`mysql`或者`vagrant`中的信息匹配。

以`Java Application ` 运行`ServiceWebApplication.java`

### 运行前台

```shell
npm start
```

默认用户：admin/123456

### 界面

![](.\images\login.jpg)

![](.\images\home-permission.jpg)

![](.\images\home-role-list.jpg)

![](.\images\home-role-update.jpg)

![](.\images\home-user-list.jpg)

### Swagger Api

![](.\images\swagger.jpg)