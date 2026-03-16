# 后端1000

----------------------------------------------

## 结构

├───main
│   ├───java
│   │   └───com
│   │       └───lyj
│   │           └───job1000
│   │               │   Job1000Application.java
│   │               │
│   │               ├───config
│   │               │       RestTemplateConfig.java
│   │               │
│   │               └───controller
│   │                       ProxyController.java
│   │
│   └───resources
│       │   application.properties
│       │
│       ├───static
│       └───templates
└───test
    └───java
        └───com
            └───lyj
                └───job1000
                        Job1000ApplicationTests.java

--------------------------------------------------

## 题目

- 这个任务即在**建立一个新的模块**，再用它调用0111的模块

- 为保证两个模块同时进行，这个新的模块需要在application.properties里设定一个新端口，我给的是8081

  ```
  server.port=8081

- 需要使用到RestTemplate，为规范使用，在这里建立一个config

  ```
  @Configuration
  public class RestTemplateConfig {
      @Bean
      public RestTemplate restTemplate() {
          return new RestTemplate();
      }
  }

- 再然后就是建立代理Controller(利用LinkedMultiValueMap封装post信息)

  ```
  @RestController
  public class ProxyController {
      @Autowired
      private RestTemplate restTemplate;
  
      //模块一的地址
      private final String firstUrl="http://localhost:8080/user/login";
  
      @RequestMapping("/proxy/login")
      public String proxyLogin(String username,String password){
          //需要对应模块一，故采用这种方式来封装post信息格式
          LinkedMultiValueMap<String,String> post=new LinkedMultiValueMap<>();
          post.add("username",username);
          post.add("password",password);
          String response=restTemplate.postForObject(firstUrl,post,String.class);
          return "代理返回:"+response;
      }
  }

- 测试信息(我用的是apifox软件)

  <div style="margin: 10px 0;">
              <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1000/1000test1.png" style="width:80%;" />
            </div>
  
  <div style="margin: 10px 0;">
              <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1000/1000test2.png" style="width:80%;" />
            </div>         