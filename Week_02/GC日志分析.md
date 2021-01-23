# GC日志分析

## 使用GCLogAnalysis.java演示GC
SerialGC日志：
```less
java -Xmx128m -Xms128m -XX:+UseSerialGC GCLogAnalysis
正在执行...
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at java.util.Arrays.copyOf(Arrays.java:3332)
        at java.lang.AbstractStringBuilder.ensureCapacityInternal(AbstractStringBuilder.java:124)
        at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:674)
        at java.lang.StringBuilder.append(StringBuilder.java:208)
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:54)
        at GCLogAnalysis.main(GCLogAnalysis.java:23)

java -Xmx512m -Xms512m -XX:+UseSerialGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：8916

java -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：9600

java -Xmx2g -Xms2g -XX:+UseSerialGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：9104

java -Xmx4g -Xms4g -XX:+UseSerialGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：6213

---------------------------------------------
java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2021-01-22T16:48:30.932+0800: [GC (Allocation Failure) 2021-01-22T16:48:30.932+0800: [DefNew: 139716K->17472K(157248K), 0.0260048 secs] 139716K->42683K(506816K), 0.0267229 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
2021-01-22T16:48:30.979+0800: [GC (Allocation Failure) 2021-01-22T16:48:30.979+0800: [DefNew: 157248K->17472K(157248K), 0.0347728 secs] 182459K->89221K(506816K), 0.0353006 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2021-01-22T16:48:31.037+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.037+0800: [DefNew: 157248K->17469K(157248K), 0.0269582 secs] 228997K->131856K(506816K), 0.0278254 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
2021-01-22T16:48:31.084+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.084+0800: [DefNew: 157245K->17471K(157248K), 0.0265003 secs] 271632K->176110K(506816K), 0.0273226 secs] [Times: user=0.00 sys=0.03, real=0.03 secs]
2021-01-22T16:48:31.131+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.132+0800: [DefNew: 157247K->17471K(157248K), 0.0266047 secs] 315886K->220140K(506816K), 0.0274548 secs] [Times: user=0.03 sys=0.00, real=0.03 secs]
2021-01-22T16:48:31.183+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.184+0800: [DefNew: 157247K->17471K(157248K), 0.0280656 secs] 359916K->264109K(506816K), 0.0289943 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
2021-01-22T16:48:31.233+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.234+0800: [DefNew: 157247K->17470K(157248K), 0.0250165 secs] 403885K->304805K(506816K), 0.0261851 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
2021-01-22T16:48:31.279+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.280+0800: [DefNew: 157129K->17469K(157248K), 0.0318195 secs] 444464K->352878K(506816K), 0.0325709 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
2021-01-22T16:48:31.342+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.342+0800: [DefNew: 157245K->157245K(157248K), 0.0003816 secs]2021-01-22T16:48:31.343+0800: [Tenured: 335408K->281666K(349568K), 0.0433551 secs] 492654K->281666K(506816K), [Metaspace: 2700K->2700K(1056768K)], 0.0446788 secs] [Times: user=0.03 sys=0.00, real=0.05 secs]
2021-01-22T16:48:31.409+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.410+0800: [DefNew: 139776K->17471K(157248K), 0.0073160 secs] 421442K->325456K(506816K), 0.0080695 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2021-01-22T16:48:31.450+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.450+0800: [DefNew: 157079K->157079K(157248K), 0.0004298 secs]2021-01-22T16:48:31.451+0800: [Tenured: 307984K->297615K(349568K), 0.0464588 secs] 465064K->297615K(506816K), [Metaspace: 2700K->2700K(1056768K)], 0.0479176 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
2021-01-22T16:48:31.522+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.523+0800: [DefNew: 139776K->17471K(157248K), 0.0075114 secs] 437391K->343649K(506816K), 0.0078614 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2021-01-22T16:48:31.563+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.564+0800: [DefNew: 157247K->157247K(157248K), 0.0002277 secs]2021-01-22T16:48:31.564+0800: [Tenured: 326178K->319281K(349568K), 0.0525017 secs] 483425K->319281K(506816K), [Metaspace: 2700K->2700K(1056768K)], 0.0536003 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
2021-01-22T16:48:31.641+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.642+0800: [DefNew: 139556K->139556K(157248K), 0.0002664 secs]2021-01-22T16:48:31.642+0800: [Tenured: 319281K->310862K(349568K), 0.0573476 secs] 458837K->310862K(506816K), [Metaspace: 2700K->2700K(1056768K)], 0.0586929 secs] [Times: user=0.05 sys=0.00, real=0.06 secs]
2021-01-22T16:48:31.729+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.730+0800: [DefNew: 139776K->139776K(157248K), 0.0003360 secs]2021-01-22T16:48:31.730+0800: [Tenured: 310862K->330540K(349568K), 0.0368756 secs] 450638K->330540K(506816K), [Metaspace: 2700K->2700K(1056768K)], 0.0381012 secs] [Times: user=0.05 sys=0.00, real=0.04 secs]
2021-01-22T16:48:31.794+0800: [GC (Allocation Failure) 2021-01-22T16:48:31.794+0800: [DefNew: 139776K->139776K(157248K), 0.0002230 secs]2021-01-22T16:48:31.795+0800: [Tenured: 330540K->332778K(349568K), 0.0445294 secs] 470316K->332778K(506816K), [Metaspace: 2700K->2700K(1056768K)], 0.0455916 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
执行结束！共生成对象的次数：8438
Heap
 def new generation   total 157248K, used 5678K [0x00000000e0000000, 0x00000000eaaa0000, 0x00000000eaaa0000)
  eden space 139776K,   4% used [0x00000000e0000000, 0x00000000e058ba08, 0x00000000e8880000)
  from space 17472K,   0% used [0x00000000e8880000, 0x00000000e8880000, 0x00000000e9990000)
  to   space 17472K,   0% used [0x00000000e9990000, 0x00000000e9990000, 0x00000000eaaa0000)
 tenured generation   total 349568K, used 332778K [0x00000000eaaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 349568K,  95% used [0x00000000eaaa0000, 0x00000000fef9abf8, 0x00000000fef9ac00, 0x0000000100000000)
 Metaspace       used 2707K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
```
分析：

1. 堆内存为128m时会出现OOM
1. 堆内存增加，生成对象并没有明显增加，甚至会减少，说明系统吞吐量和内存不成正比例关系
1. 程序运行时长为1秒，SerialGC的年轻代GC和老年代GC都是单线程的，所以都会STW，GC日志详情显示GC总时间是560ms，说明业务执行的时间只有440ms



ParallelGC日志分析：
```less
java -Xmx128m -Xms128m -XX:+UseParallelGC GCLogAnalysis
正在执行...
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        at GCLogAnalysis.generateGarbage(GCLogAnalysis.java:46)
        at GCLogAnalysis.main(GCLogAnalysis.java:23)

java -Xmx512m -Xms512m -XX:+UseParallelGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：8203

java -Xmx1g -Xms1g -XX:+UseParallelGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：11157

java -Xmx2g -Xms2g -XX:+UseParallelGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：12473

java -Xmx4g -Xms4g -XX:+UseParallelGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：8472

----------------------------------------------------
java -Xmx4g -Xms4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseParallelGC GCLogAnalysis
正在执行...
2021-01-22T16:41:45.920+0800: [GC (Allocation Failure) [PSYoungGen: 1048576K->174574K(1223168K)] 1048576K->237875K(4019712K), 0.0802353 secs] [Times: user=0.09 sys=0.22, real=0.08 secs]
2021-01-22T16:41:46.183+0800: [GC (Allocation Failure) [PSYoungGen: 1223150K->174589K(1223168K)] 1286451K->372292K(4019712K), 0.1134799 secs] [Times: user=0.13 sys=0.28, real=0.11 secs]
执行结束！共生成对象的次数：7995
Heap
 PSYoungGen      total 1223168K, used 216649K [0x000000076ab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 1048576K, 4% used [0x000000076ab00000,0x000000076d4132a0,0x00000007aab00000)
  from space 174592K, 99% used [0x00000007b5580000,0x00000007bffff4d8,0x00000007c0000000)
  to   space 174592K, 0% used [0x00000007aab00000,0x00000007aab00000,0x00000007b5580000)
 ParOldGen       total 2796544K, used 197703K [0x00000006c0000000, 0x000000076ab00000, 0x000000076ab00000)
  object space 2796544K, 7% used [0x00000006c0000000,0x00000006cc111ee8,0x000000076ab00000)
 Metaspace       used 2707K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
```
分析：

1. 堆内存为128m时会出现OOM
1. 程序运行时长为1秒，ParallelGC是多线程的，但是也会会STW，GC日志详情显示GC总时间是620ms，说明业务执行的时间只有380ms
1. 堆内存设置为4g时，发生了2次YoungGC，GC时间为80ms和113ms，时间较长，1秒的时间内有0.2秒STW，系统的吞吐量较低。



ConcMarkSweepGC日志分析：
```less
java -Xmx512m -Xms512m -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：5919

java -Xmx1g -Xms1g -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：8298

java -Xmx2g -Xms2g -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：6630

java -Xmx4g -Xms4g -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：7287
--------------------------------------------------------------------
java -Xmx1g -Xms1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+UseConcMarkSweepGC GCLogAnalysis
正在执行...
2021-01-22T17:32:35.009+0800: [GC (Allocation Failure) 2021-01-22T17:32:35.010+0800: [ParNew: 272640K->34048K(306688K), 0.0235705 secs] 272640K->90964K(1014528K), 0.0245736 secs] [Times: user=0.02 sys=0.09, real=0.02 secs]
2021-01-22T17:32:35.121+0800: [GC (Allocation Failure) 2021-01-22T17:32:35.121+0800: [ParNew: 306688K->34048K(306688K), 0.0284732 secs] 363604K->162179K(1014528K), 0.0292348 secs] [Times: user=0.03 sys=0.09, real=0.03 secs]
2021-01-22T17:32:35.209+0800: [GC (Allocation Failure) 2021-01-22T17:32:35.210+0800: [ParNew: 306688K->34048K(306688K), 0.0541500 secs] 434819K->241387K(1014528K), 0.0552320 secs] [Times: user=0.14 sys=0.00, real=0.05 secs]
2021-01-22T17:32:35.337+0800: [GC (Allocation Failure) 2021-01-22T17:32:35.338+0800: [ParNew: 306675K->34048K(306688K), 0.0546410 secs] 514015K->321922K(1014528K), 0.0553058 secs] [Times: user=0.13 sys=0.05, real=0.06 secs]
2021-01-22T17:32:35.452+0800: [GC (Allocation Failure) 2021-01-22T17:32:35.453+0800: [ParNew: 306688K->34048K(306688K), 0.0513631 secs] 594562K->398595K(1014528K), 0.0520808 secs] [Times: user=0.19 sys=0.02, real=0.05 secs]
2021-01-22T17:32:35.506+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 364547K(707840K)] 398667K(1014528K), 0.0013074 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-01-22T17:32:35.508+0800: [CMS-concurrent-mark-start]
2021-01-22T17:32:35.520+0800: [CMS-concurrent-mark: 0.012/0.012 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2021-01-22T17:32:35.520+0800: [CMS-concurrent-preclean-start]
2021-01-22T17:32:35.522+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2021-01-22T17:32:35.527+0800: [CMS-concurrent-abortable-preclean-start]
2021-01-22T17:32:35.583+0800: [GC (Allocation Failure) 2021-01-22T17:32:35.583+0800: [ParNew2021-01-22T17:32:35.632+0800: [CMS-concurrent-abortable-preclean: 0.002/0.101 secs] [Times: user=0.19 sys=0.03, real=0.11 secs]
: 305909K->34048K(306688K), 0.0535846 secs] 670457K->478210K(1014528K), 0.0542321 secs] [Times: user=0.11 sys=0.03, real=0.05 secs]
2021-01-22T17:32:35.637+0800: [GC (CMS Final Remark) [YG occupancy: 34839 K (306688 K)]2021-01-22T17:32:35.638+0800: [Rescan (parallel) , 0.0008709 secs]2021-01-22T17:32:35.639+0800: [weak refs processing, 0.0006982 secs]2021-01-22T17:32:35.640+0800: [class unloading, 0.0003510 secs]2021-01-22T17:32:35.640+0800: [scrub symbol table, 0.0004353 secs]2021-01-22T17:32:35.641+0800: [scrub string table, 0.0021404 secs][1 CMS-remark: 444162K(707840K)] 479002K(1014528K), 0.0055639 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2021-01-22T17:32:35.647+0800: [CMS-concurrent-sweep-start]
2021-01-22T17:32:35.649+0800: [CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2021-01-22T17:32:35.650+0800: [CMS-concurrent-reset-start]
2021-01-22T17:32:35.654+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
执行结束！共生成对象的次数：6819
Heap
 par new generation   total 306688K, used 192837K [0x00000000c0000000, 0x00000000d4cc0000, 0x00000000d4cc0000)
  eden space 272640K,  58% used [0x00000000c0000000, 0x00000000c9b11498, 0x00000000d0a40000)
  from space 34048K, 100% used [0x00000000d0a40000, 0x00000000d2b80000, 0x00000000d2b80000)
  to   space 34048K,   0% used [0x00000000d2b80000, 0x00000000d2b80000, 0x00000000d4cc0000)
 concurrent mark-sweep generation total 707840K, used 311648K [0x00000000d4cc0000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 2707K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
```
分析：

1. 堆内存为128m时会出现OOM
1. 程序运行时长为1秒，ConcMarkSweepGC在Initial Mark和Final Remark阶段会STW，加上前面的Young区GC的时间，总的STW的时间为132ms，业务执行时间为868ms。



G1GC日志分析：
```less
E:\JAVA-Advanced\homework\week_2>java -Xmx512m -Xms512m -XX:+UseG1GC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：6083

E:\JAVA-Advanced\homework\week_2>java -Xmx1g -Xms1g -XX:+UseG1GC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：6540

E:\JAVA-Advanced\homework\week_2>java -Xmx2g -Xms2g -XX:+UseG1GC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：7766

E:\JAVA-Advanced\homework\week_2>java -Xmx4g -Xms4g -XX:+UseG1GC GCLogAnalysis
正在执行...
执行结束！共生成对象的次数：9166
```
分析：

1. 堆内存为128m时会出现OOM
1. 程序运行时长为1秒，G1GC在步骤2的remark和cleanup阶段会STW，但是这两个阶段耗时很小，这样G1GC的吞吐量相对高一些。



## 压测工具演示GC
```less
--SerialGC
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseSerialGC -XX:+PrintGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
>>启动时发生2次FULL GC，和1个Young GC
sb -u http://localhost:8088/api/hello -c 40 -n 100000
>>压测时没有FULL GC，发生了9次Young GC

--ParallelGC
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseParallelGC -XX:+PrintGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
>>启动时发生1次FULL GC(Metadata GC Threshold)，和3个Young GC(Metadata GC Threshold)
sb -u http://localhost:8088/api/hello -c 40 -n 100000
>>压测时没有FULL GC，发生了9次Young GC

--ConcMarkSweepGC
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseConcMarkSweepGC -XX:+PrintGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
>>启动时，发生了2次Young GC，1次Initial Mark和Final Remark
sb -u http://localhost:8088/api/hello -c 40 -n 100000
>>压测时没有FULL GC，发生了9次Young GC

--G1GC
java -Xmx1g -Xms1g -XX:-UseAdaptiveSizePolicy -XX:+UseG1GC -XX:+PrintGC  -jar gateway-server-0.0.1-SNAPSHOT.jar
>>启动时，发生了G1GC
sb -u http://localhost:8088/api/hello -c 40 -n 100000
>>压测时只发生了4次纯年轻代模式转移暂停（Evacuation Pause: young）
```
结论：

1.  通过4种GC策略启动程序，启动时都会GC，这是由于启动时有大量的对象创建，Young区满了以后，后来新增的对象会分配的Old区，最终导致FULL GC
1. 压测时，四种GC策略都是只针对Young区GC，没有老年代的GC，说明压测时，没有创建特别多的对象
1. 使用ParallelGC策略启动时，GC都是Metadata GC Threshold，说明都是元数据区导致的GC，通过`-XX:MetaspaceSize=64m`增加元数据区域的初始化大小，启动后，没有了FULL GC，YOung GC也是只有分配失败导致的GC
### 以上GC日志分析可以得出以下结论：

1. 堆内存太小会导致OOM
1. 堆内存太大YoungGC耗时太长，吞吐量较低，系统暂停过长。因此在尽量不影响系统性能的情况下，堆内存尽量小。
1. ConcMarkSweepGC和G1GC只有小部分的标记阶段需要STW，其他时间都是和业务线程并行执行的，所以GC过程对业务影响较小。
1. GC一般在程序启动时比较频繁，甚至会出现FULL GC，程序跑起来以后只有少量的Young GC，很少出现FULL GC，可以通过适当的调整不同区域的初始化参数，减少启动时频繁的GC
