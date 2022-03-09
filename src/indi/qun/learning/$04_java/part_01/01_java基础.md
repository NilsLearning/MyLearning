---
概要: 围绕 Java 语言基本特性和机制，由点带面，让你构建牢固的 Java 技术功底

---

> **课程资料来源:** 极客时间-《Java 核心技术面试精讲》 杨晓峰

### 1.谈谈你对JAVA的理解
java是一个面向对象，跨平台，实现了垃圾自动回收的开发语言，它之所以能够跨平台，是因为在不同的操作系统需要提前安装对应操作系统的JVM
另外在目前的主流的JDK版本中，如 JDK 8 实际是解释和编译混合的一种模式，提供了 JIT（Just-In-Time）编译器，JIT 能够在运行时将热点
代码编译成机器码，这种情况下部分热点代码就属于编译执行，而不是解释执行了。

---
### 2.Exception和Error有啥区别
Exception和Error都继承了Throwable类
Exception是程序正常运行中，可能出现的情况，应该予以处理，处理方式：1.捕获 try……catch  2.向上抛出 3.交由JVM处理
![img.png](异常树.png)

- 异常处理的基本原则
  - 1.尽量不要捕获类似 Exception 这样的通用异常，而是应该捕获特定异常    
  泛泛的 Exception 之类，恰恰隐藏了我们的目的。另外，我们也要保证程序不会捕获到我们不希望捕获的异常。比如，你可能更希望 RuntimeException 被扩散出来，而不是被捕获
  - 2.不要生吞（swallow）异常
  生吞异常，往往是基于假设这段代码可能不会发生，或者感觉忽略异常是无所谓的，但是千万不要在产品代码做这种假设！好的方式：
  使用产品日志，详细地输出到日志系统里
  - 3.Throw early, catch late
  例如以下❌代码
    ```
    public void readPreferences(String fileName){
     //...perform operations... 
    InputStream in = new FileInputStream(fileName);
     //...read the preferences file...
    }
    ```
    如果 fileName 是 null，那么程序就会抛出 NullPointerException，但是由于没有第一时间暴露出问题，堆栈信息可能非常令人费解，往往需要相对复杂的定位    
  正确代码：
    ``` 
    public void readPreferences(String filename) {
    Objects. requireNonNull(filename);
    //...perform other operations... 
    InputStream in = new FileInputStream(filename);
    //...read the preferences file...
    }
    ```
- Java 的异常处理机制对性能的影响:
  - 1.try-catch 代码段会产生额外的性能开销，或者换个角度说，它往往会影响 JVM 对代码进行优化，所以建议仅捕获有必要的代码段，尽量不要一个大的 try 包住整段的代码；与此同时，
  利用异常控制代码流程，也不是一个好主意，远比我们通常意义上的条件语句（if/else、switch）要低效        
  - 2.Java 每实例化一个 Exception，都会对当时的栈进行快照，这是一个相对比较重的操作。如果发生的非常频繁，这个开销可就不能被忽略了
---

### 3.谈谈final，finally，finalize有什么不同
- final 可以用来修饰类、方法、变量，分别有不同的意义，final 修饰的 class 代表不可以继承扩展，final 的变量是不可以修改的，而 final 的方法也是不可以重写的（override）   
- finally 则是 Java 保证重点代码一定要被执行的一种机制。我们可以使用 try-finally 或者 try-catch-finally 来进行类似关闭 JDBC 连接、保证 unlock 锁等动作   
- finalize 是基础类 java.lang.Object 的一个方法，它的设计目的是保证对象在被垃圾收集前完成特定资源的回收。finalize 机制现在已经不推荐使用，并且在 JDK 9 开始被标记为 deprecated
> - final的好处
>   - 使用 final 修饰参数或者变量，也可以清楚地避免意外赋值导致的编程错误，甚至，有人明确推荐将所有方法参数、本地变量、成员变量声明成 final    
>   - final 变量产生了某种程度的不可变（immutable）的效果，所以，可以用于保护只读数据，尤其是在并发编程中，因为明确地不能再赋值 final 变量，有利于减少额外的同步开销，也可以省去一些防御性拷贝的必要
> - 关于finally
>   - 更推荐使用 Java 7 中添加的 try-with-resources 语句，因为通常 Java 平台能够更好地处理异常情况，编码量也要少很多，何乐而不为呢
> - 为什么finalize被弃用
>   - 你无法保证 finalize 什么时候执行，执行的是否符合预期。使用不当会影响性能，导致程序死锁、挂起等。
#### 3.1 final 不是 immutable
比如如下代码：
```
 final List<String> strList = new ArrayList<>();
 strList.add("Hello");
 strList.add("world");  
 List<String> unmodifiableStrList = List.of("hello", "world");
 unmodifiableStrList.add("again");
```
final 只能约束 strList 这个引用不可以被赋值，但是 strList 对象行为不被 final 影响，添加元素等操作是完全正常的。如果我们真的希望对象本身是不可变的，那么需要相应的类支持不可变的行为。在上面这个例子中，
List.of 方法创建的本身就是不可变 List，最后那句 add 是会在运行时抛出异常的    

> Immutable 在很多场景是非常棒的选择，某种意义上说，Java 语言目前并没有原生的不可变支持，如果要实现 immutable 的类，我们需要做到:
>   - 将 class 自身声明为 final，这样别人就不能扩展来绕过限制了。
>   - 将所有成员变量定义为 private 和 final，并且不要实现 setter 方法。
>   - 通常构造对象时，成员变量使用深度拷贝来初始化，而不是直接赋值，这是一种防御措施，因为你无法确定输入对象不被其他人修改。
>   - 如果确实需要实现 getter 方法，或者其他可能会返回内部状态的方法，使用 copy-on-write 原则，创建私有的 copy

#### 3.2 有什么机制可以替换 finalize 吗?
Java 平台目前在逐步使用 java.lang.ref.Cleaner 来替换掉原有的 finalize 实现。Cleaner 的实现利用了幻象引用（PhantomReference），这是一种常见的所谓 post-mortem 清理机制