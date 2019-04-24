package com.xairlab.otus.json.entity;

public class WithObject {

    private Plain plain;

    @Override
    public String toString() {
        return "WithObject{" +
                "plain=" + plain +
                '}';
    }

    public WithObject(Plain plain) {
        this.plain = plain;
    }
}
