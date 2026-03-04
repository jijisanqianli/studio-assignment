# 后端-0011

-------------------------------------------------------------

## 结构

back-end0011/
├── README.md
└── src/
    ├── first/
    │   ├── Bakery.java
    │   ├── BreadCooker.java
    │   ├── BreadEater.java
    │   ├── Consumer.java
    │   ├── Producer.java
    │   ├── Product.java
    │   └── Test.java
    ├── second/
    │   ├── SynchronizedBlock/
    │   │   ├── Creep.java
    │   │   └── Test.java
    │   └── SynchronizedMethod/
    │       ├── Creep.java
    │       └── Test.java
    └── third/
        ├── MyLinkedList.java
        ├── MyNode.java
        └── Test.java

## 一些本节任务的回答

- 什么是**进程**，什么是**线程**：

  > **进程**：是**操作系统分配资源的基本单位**，简单来说就是一个程序。每个进程都有独立的内存空间、等系统资源，进程之间相互隔离，一个进程崩溃通常不会影响其他进程。
  >
  > **线程**：这是进程内的**执行单元**，是 CPU 调度和分派的基本单位。一个进程可以包含多个线程，它们共享进程的内存空间和资源。
  
- 进程和线程之间的**联系**：

  > - 一个进程可以包含多个线程，多线程可以并发执行，提高程序的响应速度和资源利用率。
  >
  > - 线程依赖于进程而存在，进程结束后，其所有线程也会被终止。

--------------------------------------------

## 题目完成

### 题目1

- 首先按要求对生产者和消费者形成接口，生产者一定会有*produce(生产)*这一行为,消费者也一定会有*consume(消费)*这一行为，再加上一些其他返回值的功能，就可以形成接口如下

  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Producer.png" 
     style="width:45%; display: inline-block; margin-right: 10px;" 
     align="left">
  
  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Consumer.png" 
     style="width:45%; display: inline-block; margin-right: 10px;" 
     align="left">


- 为精简时间，就打算建立一个“面包买卖”的简单生产者消费者模型，生产者就是BreadCooker,消费者就是BreadEater，BreadEater有吃面包的最大个数，BreadCooker将会不断生产面包直至每个BreadEater吃饱

- 建立了一个Product类来作为生产与消费的对象，具有变量name和id

  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Bakery.png" 
     style="width:45%; display: inline-block; margin-right: 10px;" 
     align="left">


- 而后考虑系统的建立：需要一个Bakery(面包房)来作为**中间站**，存储BreadCooker生产出的面包，然后又在这里将面包卖给BreadEater。面包房需要考虑面包**最大容纳量**maxBreadNum，存储面包的一个**库**(用ArrayList<Product>来实现)。

- **最重要的一点**（不使用锁的问题）：整个系统为避免多个线程的互相冲突导致可能的*面包量溢出*、*输出序号紊乱*、*不能正常及时停止*等问题，需要为线程资源***上锁***，同时要达到正确的效果，必须都使用***同一把锁***（写的时候就因为尝试采用多种上锁方式导致产生bug），这里选择在Bakery中定义这把锁

   <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Bakery.png" 
     style="width:45%; display: inline-block; margin-right: 10px;" 
     align="left">
   
- 之后进行线程的搭建，我选用了"实现Runnable接口的方式"进行，BreadCooker和BreadEater都实现Runnable接口，并重写run方法

- BreadCooker类的重要代码：

   <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011BreadCooker(1).png" 
     style="width:45%; display: inline-block; margin-right: 10px;" 
     align="left">
   
   <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011BreadCooker(2).png" 
     style="width:80%; display: inline-block; margin-right: 10px;" 
     align="left">
   
   <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011BreadCooker(3).png" 
     style="width:100%; display: inline-block; margin-right: 10px;" 
     align="left">  


- 同理，BreadEater类的重要代码：

   <div style="margin: 10px 0;">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011BreadEater(1).png" style="width:45%;" />
  </div>
  
   <div style="margin: 10px 0;">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011BreadEater(2).png" style="width:60%;" />
  </div>

   <div style="margin: 10px 0;">
    <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011BreadEater(3).png" style="width:75%;" />
  </div>

- 最后是运行的Test类 

  <div style="margin: 10px 0;">
      <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Test.png" style="width:40%;" />
    </div>

- 一次运行结果的部分内容

  <div style="margin: 10px 0;">
        <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011result.png" style="width:40%;" />
      </div>

### 题目2

- 同步块

  <div style="margin: 10px 0;">
        <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Creep(1).png" style="width:40%;" />
      </div>
  
   <div style="margin: 10px 0;">
        <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Test(1).png" style="width:25%;" />
      </div>

- 同步方法

  <div style="margin: 10px 0;">
          <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Creep(2).png" style="width:40%;" />
        </div>

     <div style="margin: 10px 0;">
          <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011Test(2).png" style="width:30%;" />
        </div>

## 题目三

- 首先定义一个自己的节点类（使用泛型）

  <div style="margin: 10px 0;">
            <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/1011MyNode.png" style="width:20%;" />
          </div>

- 链表的初级结构搭建

  <div style="margin: 10px 0;">
              <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/MyLinkedList/1011MyLinkedList.png" style="width:20%;" />
            </div>

- 所需API如下
  - `boolean addList(Node node)` 添加结点
  
    <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/MyLinkedList/1011addList.png" style="width:30%;" />
                </div>
  
  - `void removeList0()` 删除尾结点
  
    <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/MyLinkedList/1011remove0.png" style="width:30%;" />
                </div>
  
  - `void removelistByValue(E value)` 根据节点值删除节点（原本的形参类型是int，但理应是泛型E，该接口与下面均已修改）（这个功能能删除**所有**符合条件的节点，为此我还创建了一个根据索引删除节点的接口`public boolean removeListByIndex(int index)`)
  
    <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/MyLinkedList/1011removeValue.png" style="width:40%;" />
                </div>
    
    <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/MyLinkedList/1011removeIndex.png" style="width:40%;" />
                </div>
  
  - `int find(E value)` 找到值为 value 的结点，返回这个结点下标（下标从 0 开始计算）
  
    <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/MyLinkedList/1011find.png" style="width:25%;" />
                </div>