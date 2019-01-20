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

        TestB t1 = new TestB();
        TestB2 t2 = new TestB2();
        TestB4 t3 = new TestB4();
        TestInt t4 = new TestInt();
        TestInt2 t5 = new TestInt2();
        TestInt4 t6 = new TestInt4();
        Mix t7 = new Mix();

        measure.printSizeObject(t1);
        measure.printSizeObject(t2);
        measure.printSizeObject(t3);
        measure.printSizeObject(t4);
        measure.printSizeObject(t5);
        measure.printSizeObject(t6);
        measure.printSizeObject(t7);
    }

    public static void sizeOfCollections(Measure measure){

        Integer[] t8 = new Integer[5];
        List<Boolean> t9 = new ArrayList<>();
        t9.add(Boolean.TRUE);
        t9.add(Boolean.TRUE);
        t9.add(Boolean.TRUE);
        t9.add(Boolean.FALSE);
        t9.add(Boolean.TRUE);
        Map<String, Integer> t10 = new TreeMap<>();
        t10.put("A", 10);
        t10.put("B", 20);

        measure.printSizeObject(t8);
        measure.printSizeObject(t9);
        measure.printSizeObject(t10);
    }
}


