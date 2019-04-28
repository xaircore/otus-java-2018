package com.xairlab.otus.gc;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.*;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;


public class GcDemo {
    public static void main(String... args) throws Exception {
        long beginTime = System.currentTimeMillis();

        monitoring();

        int size = 5* 1000 * 1000;
        int loopCounter = 100_000;
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.xairlab.otus.gc:type=Benchmark");
        Benchmark mbean = new Benchmark(loopCounter);
        mbs.registerMBean(mbean, name);
        mbean.setSize(size);
        mbean.run();

        long endTime = System.currentTimeMillis();
        System.out.println("Duration: " + (endTime - beginTime) / 1000);
    }

    private static void monitoring() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gcBean : gcBeans) {
            System.out.println("GC name: " + gcBean.getName());
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    System.out.println("start:" + info.getGcInfo().getStartTime() + " Name:" + info.getGcName() + ", action:" + info.getGcAction() + ", gcCause:" + info.getGcCause() + "(" + info.getGcInfo().getDuration() + " ms)");
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
