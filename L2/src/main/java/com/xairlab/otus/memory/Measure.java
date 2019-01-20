package com.xairlab.otus.memory;

import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;
import org.openjdk.jol.info.ClassLayout;

public class Measure {

    final private VirtualMachine vm;

    public Measure() {
        this.vm = VM.current();
    }

    public void printSize(boolean item) {
        System.out.println(item);
        vm.sizeOfField("boolean");
    }

    public void printSize(byte item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("byte"));
    }

    public void printSize(short item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("short"));
    }

    public void printSize(char item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("char"));
    }

    public void printSize(int item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("int"));
    }

    public void printSize(long item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("long"));
    }

    public void printSize(float item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("float"));
    }

    public void printSize(double item) {
        System.out.println(item);
        System.out.println(vm.sizeOfField("double"));
    }

    public void printSizeClass(Class item) {
        System.out.println(item);
        System.out.println(ClassLayout.parseClass(item).headerSize());
        System.out.println(ClassLayout.parseClass(item).instanceSize());

    }

    public void printSizeObject(Object item){
        System.out.println(item);
        System.out.println(ClassLayout.parseInstance(item).instanceSize());
    }
}
