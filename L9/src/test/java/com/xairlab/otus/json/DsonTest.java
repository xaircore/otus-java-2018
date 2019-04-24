package com.xairlab.otus.json;

import com.xairlab.otus.json.dson.Dson;
import com.xairlab.otus.json.entity.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DsonTest {

    @Test
    void empty() {
        Dson dson = new Dson(new Object());
        assertEquals("{}", dson.toJson());
    }

    @Test
    void plain() {
        Plain plain = new Plain(19, 'a', true, 85.2);
        Dson dson = new Dson(plain);
        assertEquals("{\"age\":19,\"name\":\"a\",\"sex\":true,\"weight\":85.2}", dson.toJson());
    }

    @Test
    void string() {
        WithString withString = new WithString((short) 15, "ara");
        Dson dson = new Dson(withString);
        assertEquals("{\"len\":15,\"name\":\"ara\"}", dson.toJson());
    }

    @Test
    void array(){
        WithArray withArray = new WithArray(new int[]{4, 8, 15, 16, 23, 45}, 451.0F);
        Dson dson = new Dson(withArray);
        assertEquals("{\"tokens\":[4,8,15,16,23,45],\"cost\":451.0}", dson.toJson());
    }

    @Test
    void collection(){
        List<Integer> numbers = Arrays.asList(4, 8, 15, 16, 23, 45);
        WithCollection<Integer> withCollection = new WithCollection<>(numbers);
        Dson dson = new Dson(withCollection);
        assertEquals("{\"collection\":[4,8,15,16,23,45]}", dson.toJson());
    }

    @Test
    void object(){
        Plain plain = new Plain(19, 'a', true, 85.2);
        WithObject withObject = new WithObject(plain);
        Dson dson = new Dson(withObject);
        assertEquals("{\"plain\":\"{\\\"age\\\":19,\\\"name\\\":\\\"a\\\",\\\"sex\\\":true,\\\"weight\\\":85.2}\"}", dson.toJson());
    }

    @Test
    void annotation() {
        WithAnnotation withAnnotation = new WithAnnotation(19, true);
        Dson dson = new Dson(withAnnotation);
        assertEquals("{\"vova\":19,\"sex\":true}", dson.toJson());
    }
}
