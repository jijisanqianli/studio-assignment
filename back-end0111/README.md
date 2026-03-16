# 后端0111

----------------------------------------------

## 结构

├───java
│   └───com
│       └───lyj
│           └───job0111
│               │   Job0111Application.java
│               │
│               ├───config
│               │       SecurityConfig.java
│               │
│               ├───controller
│               │       LoginPageController.java
│               │       UserController.java
│               │
│               ├───dao
│               │       UserMapper.java
│               │
│               ├───pojo
│               │       Result.java
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

--------------------------------------------------

## 题目

> 该题目实际与0101差距很小，故基本上与0101的代码差不多，只有部分进行了增添与修改，以下为增改部分

- **SpringSecurity**的使用

  - 通过在项目的pom.xml引入SpringSecurity依赖使用SpringSecurity

  - 这个依赖会自动拦截所有请求重定向至/login，但实际该项目用户的请求**不应该被拦截**

  - 自带的**csrf**防护功能会影响表单的提交

  - 故需要针对此写config来进行SpringSecurity的配置以实现正常功能

    ```
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/user/loginPage", "/user/login","/login_page.html").permitAll()  //只有登录页和登录接口开放
                            .anyRequest().authenticated()                                    //其他都要登录
                    )
                    .csrf(csrf -> csrf.disable())//关掉CSRF
                    .formLogin(form -> form.permitAll());  // 启用默认表单登录
            return http.build();
        }
    }
    
  - 此时访问其他请求如/test时

    <div style="margin: 10px 0;">
                <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0111security.png" style="width:40%;" />
              </div>

  - 在service层还使用了SpringSecurity的BCryptPasswordEncoder实现密码的加密功能

    ```
     @Autowired
        private UserMapper userMapper;
    
        // 加密工具
        private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
        public String login(String username, String password) {
            User user = userMapper.getUserByUsername(username);
    
            if (user == null) {
                // 注册，密码加密存储
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(encoder.encode(password));
                userMapper.registerNewUser(newUser);
                return "注册成功";
            }
    
            // 登录验证
            if (encoder.matches(password, user.getPassword())) {
                return "登录成功";
            } else {
                return "密码错误";
            }
        }

- 采用**统一响应结果封装类**Result

  - 实现结果格式的统一化
  
    ```
    public class Result{
        private boolean flag;
        private Object data;
    
        public static Result success(Object data) {
            Result r = new Result();
            r.flag = true;
            r.data = data;
            return r;
        }
    
        public static Result fail() {
            Result r = new Result();
            r.flag = false;
            return r;
        }
    
        public boolean isFlag() { return flag; }
        public Object getData() { return data; }
    }
    
   - 在controller中的运用
  
     ```
     @PostMapping("/login")
         public Result login(String username, String password) {
             String res = userService.login(username, password);
             if (res.equals("登录成功") || res.equals("注册成功")) {
                 return Result.success(res);
             }
             return Result.fail();
         }