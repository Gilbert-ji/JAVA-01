# JVM

该模块点学习了字节码技术、JVM类加载器、JVM内存模型以及GC算法

### 字节码技术

#### 什么是字节码？

Java bytecode 由单字节（byte）的指令组成，理论上最多支持 256 个操作码（opcode）。实际上 Java 只使用了200左右的操作码， 还有一些操作码则保留给调试操作  。

#### 相关命令

编译 `javac demo/jvm0104/HelloByteCode.java`  

查看字节码 `javap -c -verbose demo.jvm0104.HelloByteCode`  

### JVM类加载器

- 类的生命周期
- 类的加载时机
- 三类加载器
- 自定义ClassLoader

### JVM内存模型

#### 堆

- 存放对象实例

- 分为young区和older区，young区分为Eden ，Survivor0和survivor1。

#### 线程栈

- 程序寄存器：保存线程运行的指令地址
- 本地方法栈：调用JVM的native方法的辅助栈。其中存放的是栈帧
- 虚拟机栈：为自定义的java方法服务，栈的深度取决于栈调用的深度。

#### 非堆

- 编译后的类信息，包括方法信息，静态变量，finaly修饰的变量
- 常量池也在方法区中，包括class文件信息和运行时常量池

### GC策略

#### GC算法

* 清除算法
* 复制算法
* 整理算法

#### GC的具体实现

- 串行GC（Serial GC）
- 并行GC（Parallel GC）
- CMS垃圾收集器 
- G1垃圾收集器