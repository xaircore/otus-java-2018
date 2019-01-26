package com.xairlab.otus.memory;

import java.util.*;

public class Memory {

    public static void main(String[] args) {

        Measure measure = new Measure();

        measure.printSizeClass(Memory.class);

        Memory.sizeOfPrimitives(measure);

        Memory.sizeOfObjects(measure);

        Memory.sizeOfCollections(measure);
    }

    public static void sizeOfPrimitives(Measure measure) {

        boolean b = true;
        byte by = 5;
        short s = 10;
        int i = 15;
        char c = 'A';
        long l = 20;
        float f = 25.0f;
        double d = 30.0;

        measure.printSize(b);
        measure.printSize(by);
        measure.printSize(s);
        measure.printSize(i);
        measure.printSize(c);
        measure.printSize(l);
        measure.printSize(f);
        measure.printSize(d);
    }

    public static void sizeOfObjects(Measure measure) {

        measure.printSizeObject(() -> new TestB());
        measure.printSizeObject(() -> new TestB2());
        measure.printSizeObject(() -> new TestB4());
        measure.printSizeObject(() -> new TestInt());
        measure.printSizeObject(() -> new TestInt2());
        measure.printSizeObject(() -> new TestInt4());
        measure.printSizeObject(() -> new Mix());
    }

    public static void sizeOfCollections(Measure measure) {


        measure.printSizeObject(() -> new Integer[5]);

        measure.printSizeObject(() -> {
            List<Boolean> t9 = new ArrayList<>();
            t9.add(Boolean.TRUE);
            t9.add(Boolean.TRUE);
            t9.add(Boolean.TRUE);
            t9.add(Boolean.FALSE);
            t9.add(Boolean.TRUE);
            return t9;
        });

        measure.printSizeObject(() -> {
            Map<String, Integer> t10 = new TreeMap<>();
            t10.put("A", 10);
            t10.put("B", 20);
            return t10;
        });
    }
}


