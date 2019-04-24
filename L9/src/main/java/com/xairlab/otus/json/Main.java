package com.xairlab.otus.json;

import com.xairlab.otus.json.dson.Dson;
import com.xairlab.otus.json.entity.WithAnnotation;

public class Main {

    public static void main(String[] args) {
        WithAnnotation withAnnotation = new WithAnnotation(19, true);
        Dson dson = new Dson(withAnnotation);
        System.out.println(dson.toJson());
    }
}