package com.gmail.mosoft521.ch02.perm;

import java.util.HashMap;

/**
 * JDK1.6 1.7 -XX:+PrintGCDetails -XX:PermSize=5M -XX:MaxPermSize=5m
 * <p>
 * JDK1.8 -XX:+PrintGCDetails -XX:MaxMetaspaceSize=5M
 *
 * @author Geym
 */
public class PermTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            for (i = 0; i < 100000; i++) {
                CglibBean bean = new CglibBean("geym.zbase.ch2.perm" + i, new HashMap());
            }
        } catch (Exception e) {
            System.out.println("total create count:" + i);
            throw e;
        }
    }
}
/*
[GC (Metadata GC Threshold) [PSYoungGen: 4926K->1211K(18944K)] 4926K->1219K(62976K), 0.0286192 secs] [Times: user=0.05 sys=0.00, real=0.03 secs]
[Full GC (Metadata GC Threshold) [PSYoungGen: 1211K->0K(18944K)] [ParOldGen: 8K->1099K(32256K)] 1219K->1099K(51200K), [Metaspace: 4055K->4055K(1056768K)], 0.0141232 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
[GC (Last ditch collection) [PSYoungGen: 0K->0K(18944K)] 1099K->1099K(51200K), 0.0005138 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Last ditch collection) [PSYoungGen: 0K->0K(18944K)] [ParOldGen: 1099K->1041K(62976K)] 1099K->1041K(81920K), [Metaspace: 4055K->4055K(1056768K)], 0.0091786 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Metadata GC Threshold) [PSYoungGen: 327K->32K(18944K)] 1369K->1073K(81920K), 0.0040550 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Metadata GC Threshold) [PSYoungGen: 32K->0K(18944K)] [ParOldGen: 1041K->700K(94720K)] 1073K->700K(113664K), [Metaspace: 4067K->4067K(1056768K)], 0.0126064 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
[GC (Last ditch collection) [PSYoungGen: 0K->0K(21504K)] 700K->700K(116224K), 0.0008559 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Last ditch collection) [PSYoungGen: 0K->0K(21504K)] [ParOldGen: 700K->697K(145408K)] 700K->697K(166912K), [Metaspace: 4067K->4067K(1056768K)], 0.0113519 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Metadata GC Threshold) [PSYoungGen: 378K->96K(21504K)] 1076K->793K(166912K), 0.0004224 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Metadata GC Threshold) [PSYoungGen: 96K->0K(21504K)] [ParOldGen: 697K->696K(197632K)] 793K->696K(219136K), [Metaspace: 4067K->4067K(1056768K)], 0.0046027 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
[GC (Last ditch collection) [PSYoungGen: 0K->0K(22528K)] 696K->696K(220160K), 0.0002820 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
[Full GC (Last ditch collection) [PSYoungGen: 0K->0K(22528K)] [ParOldGen: 696K->696K(269824K)] 696K->696K(292352K), [Metaspace: 4067K->4067K(1056768K)], 0.0044193 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
	at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:237)
	at net.sf.cglib.beans.BeanMap$Generator.create(BeanMap.java:122)
	at net.sf.cglib.beans.BeanMap.create(BeanMap.java:58)
	at com.gmail.mosoft521.ch02.perm.CglibBean.<init>(CglibBean.java:35)
	at com.gmail.mosoft521.ch02.perm.PermTest.main(PermTest.java:17)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
Heap
 PSYoungGen      total 22528K, used 1259K [0x00000000eab00000, 0x00000000ec280000, 0x0000000100000000)
  eden space 20992K, 6% used [0x00000000eab00000,0x00000000eac3afc0,0x00000000ebf80000)
  from space 1536K, 0% used [0x00000000ec100000,0x00000000ec100000,0x00000000ec280000)
  to   space 1536K, 0% used [0x00000000ebf80000,0x00000000ebf80000,0x00000000ec100000)
 ParOldGen       total 269824K, used 696K [0x00000000c0000000, 0x00000000d0780000, 0x00000000eab00000)
  object space 269824K, 0% used [0x00000000c0000000,0x00000000c00ae3e8,0x00000000d0780000)
 Metaspace       used 4100K, capacity 5030K, committed 5120K, reserved 1056768K
  class space    used 459K, capacity 465K, committed 512K, reserved 1048576K
 */