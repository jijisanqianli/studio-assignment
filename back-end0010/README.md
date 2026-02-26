# 后端-0010
-------------------------------------------------------------
## 结构
back-end0010/
├── README.md
└── src/
    ├── first/
    │   ├── Circle.java
    │   ├── Perarea.java
    │   ├── Rectangle.java
    │   └── test.java
    └── second/
        ├── PlainRect.java
        ├── Rect.java
        └── test.java

## 一些本节任务的回答

- Java的**三大基本特征**是什么：

  > 我认为这是面向对象编程的核心特性，分别是：
  >
  > **1. 封装**：把数据和方法包装在类内部，通过访问修饰符控制外部访问，隐藏实现细节。
  >
  > **2. 继承**：子类可以继承父类的属性和方法，实现代码复用，用`extends`关键字。
  >
  > **3. 多态**：同一操作作用于不同对象，产生不同结果。包括编译时多态（方法重载）和运行时多态（方法重写）。

- **抽象类**是什么：

  > 我认为这是一种**不能被实例化的类**，用`abstract`修饰。它既可以有抽象方法（只有声明没有实现），也可以有普通方法（已实现的方法）。子类继承抽象类后，必须实现所有抽象方法，否则子类也必须是抽象类。

- **接口**是什么：

  > 我认为这是一种**完全抽象的“契约”**，用`interface`定义。接口里的方法默认是`public abstract`（Java 8之前），变量默认是`public static final`。类用`implements`实现接口，**必须实现接口中所有方法**，且一个类可以实现多个接口。

- **抽象类 vs 接口**：

  > | 对比点        | 抽象类                   | 接口                                                    |
  > | ------------- | ------------------------ | ------------------------------------------------------- |
  > | **关键字**    | `abstract class`         | `interface`                                             |
  > | **实例化**    | 不能                     | 不能                                                    |
  > | **方法实现**  | 可以有抽象方法和普通方法 | Java 8前全是抽象方法，之后可以有`default`和`static`方法 |
  > | **变量**      | 各种变量                 | 只能是`public static final`常量                         |
  > | **构造方法**  | 可以有                   | 不能有                                                  |
  > | **继承/实现** | 单继承                   | 多实现                                                  |
  > | **本质**      | “是什么”的关系           | “能做什么”的关系                                        |

--------------------------------------------

## 题目完成

### 题目1

- 按要求定义该接口Perarea
<img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-24_23-14-36.png" alt="Perarea" style="zoom: 67%;" />

- 同理完成两个类的创建
<img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-25_00-02-03.png" alt="Rectangle" style="zoom: 50%;" />

<div align="left">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-25_00-01-50.png" alt="Perarea" style="zoom:50%;" />
</div>

- 测试代码与结果

<div align="left">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-25_00-02-12.png" alt="test" style="zoom: 50%;" />
</div>

<div align="left">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-25_00-02-24.png" alt="result" style="zoom: 50%;" />
</div>

### 题目二

- 大部分思路同题目一，只展示判断那部分

<div align="left">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-26_11-18-02.png" alt="isInside" style="zoom: 50%;" />
</div>

- 测试结果如下

<div align="left">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/Snipaste_2026-02-25_00-04-31.png" alt="result" style="zoom: 50%;" />
</div>
