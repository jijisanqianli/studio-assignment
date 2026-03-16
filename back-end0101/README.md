# 后端0101

----------------------------------------------

## 结构

├───java
│   └───com
│       └───lyj
│           └───job0101
│               │   Job0101Application.java
│               │
│               ├───controller
│               │       LoginController.java
│               │       LoginPageController.java
│               │
│               ├───mapper
│               │       UserMapper.java
│               │
│               ├───pojo
│               │       User.java
│               │
│               └───service
│                       UserService.java
│
└───resources
    │   application.properties
    │
    ├───static
    │       login_page.html
    │
    └───templates

-----------------------------------------------------------------

## 一些问题的回答

- 前后端分离后如何交互
  - 前端：只写页面（HTML/JS）
  - 后端：只提供接口（API）
  - 前端通过 **HTTP 请求** 访问后端接口，后端返回 **JSON**，前端渲染页面
- JSON 在 JavaWeb 中的使用
  - JSON 是一种用于 **前后端数据传递**的数据格式字符串
  - 后端把数据转成 JSON 返回给前端，前端接收 JSON 展示内容
- XML 是什么
  - 一种用来**存数据、做配置**的文档

--------------------------------------------------

## 题目

### (一)

> 题目使用Spring框架完成

1. 创建数据库yiludb与表user

   - user对应的类（使用lombok简化）

     ```
     @Data
     @AllArgsConstructor
     @NoArgsConstructor
     public class User {
         private Integer uid;
         private String username;
         private String password;
     }
     ```

   - 表user的DDL如下

     ```
     create table user
     (
         uid      int unsigned auto_increment
             primary key,
         username varchar(20) not null,
         password varchar(20) not null,
         constraint User_pk_2
             unique (username)
     );
     ```

2. 按照MVC架构管理，即controller--service--dao三层

   - Controller:负责接收请求、返回结果，与**前端**打交道
     - LoginContoller:直接处理登录"/login"的请求
     - LoginPageContoller:用于返回前端界面，处理"/loginPage"的请求
   - Service:处理、判断业务逻辑，进行**判断**、**调用**
     - UserService:判断是否存在用户名，密码是否正确
   - Dao:与**数据库**打交道
     - UserMapper:实现查询功能、注册功能(添加)

3. 代码实现

   - LoginController(使用Map实现json的输出)

     <div style="margin: 10px 0;">
                 <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0101/0101controller1.png" style="width:50%;" />
               </div>

   - LoginPageContoller

     <div style="margin: 10px 0;">
                 <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0101/0101controller2.png" style="width:50%;" />
               </div>

   - UserService

     <div style="margin: 10px 0;">
                 <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0101/0101service.png" style="width:70%;" />
               </div>

   - UserMapper

     <div style="margin: 10px 0;">
                 <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0101/0101dao.png" style="width:70%;" />
               </div>
     
### (二)进阶

1. Maven管理

   - 完成任务在利用idea创建项目时就是利用maven直接管理所需的依赖，在文件中有展示

     <div style="margin: 10px 0;">
                 <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0101/0101maven.png" style="width:40%;" />
               </div>

2. json

   - 同样，json文件的形式转换利用Map达成，在LoginController处体现

