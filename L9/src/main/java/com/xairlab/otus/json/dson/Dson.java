package com.xairlab.otus.json.dson;

import com.xairlab.otus.json.api.JsonName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;

public class Dson {

    static Logger logger = LoggerFactory.getLogger(Dson.class);

    private JsonObjectBuilder json;
    private Object object;

    public Dson(Object object) {

        this.object = object;
        json = Json.createObjectBuilder();
        writeFields(object.getClass());
    }

    private void writeFields(Class meta) {
        for (Field f : meta.getDeclaredFields()) {
            f.setAccessible(true);
            Class type = f.getType();
            String name;
            if (f.isAnnotationPresent(JsonName.class)) {
                JsonName jsonName = f.getAnnotation(JsonName.class);
                name = jsonName.name();
            } else {
                name = f.getName();
            }
            logger.info("json value " + name + " of type " + type.getName());
            try {
                if (type == String.class) {
                    updateString(f, name);
                } else if (type.isPrimitive()) {
                    updatePrimitive(f, name, type);
                } else if (type.isArray()) {
                    updateArrayPrimitive(f, name);
                } else if (type == Collection.class) {
                    updateCollection(f, name);
                } else {
                    updateObject(f, name);
                }
            } catch (IllegalAccessException e) {
                logger.error("can't resolve " + name, e.getCause());
            }
            f.setAccessible(false);
        }
    }

    private void updateObject(Field f, String name) throws IllegalAccessException {
        json.add(name, new Dson(f.get(object)).toJson());
    }

    private void updateString(Field f, String name) {
        try {
            json.add(name, (String) f.get(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void updatePrimitive(Field f, String name, Class type) throws IllegalAccessException {
        if (type == byte.class || type == short.class || type == int.class) {
            json.add(name, f.getInt(object));
        } else if (type == long.class) {
            json.add(name, f.getLong(object));
        } else if (type == boolean.class) {
            json.add(name, f.getBoolean(object));
        } else if (type == float.class || type == double.class) {
            json.add(name, f.getDouble(object));
        } else if (type == char.class) {
            json.add(name, String.valueOf(f.getChar(object)));
        }
    }

    private void updateCollectionObject(JsonArrayBuilder jsonArray, Object data) {
        if (data instanceof Integer) {
            jsonArray.add((Integer) data);
        } else if (data instanceof Boolean) {
            jsonArray.add((Boolean) data);
        } else if (data instanceof Short) {
            jsonArray.add((Short) data);
        } else if (data instanceof Byte) {
            jsonArray.add((Byte) data);
        } else if (data instanceof Character) {
            jsonArray.add((Character) data);
        } else if (data instanceof Long) {
            jsonArray.add((Long) data);
        } else {
            jsonArray.add(String.valueOf(data));
        }
    }

    private void addArrayPrimitive(JsonArrayBuilder jsonArray, Object data, int index) {
        Class type = data.getClass().getComponentType();
        if (type == byte.class || type == short.class || type == int.class) {
            jsonArray.add((int) Array.get(data, index));
        } else if (type == long.class) {
            jsonArray.add((long) Array.get(data, index));
        } else if (type == boolean.class) {
            jsonArray.add((boolean) Array.get(data, index));
        } else if (type == float.class || type == double.class) {
            jsonArray.add((double) Array.get(data, index));
        } else if (type == char.class) {
            jsonArray.add((String) Array.get(data, index));
        }
    }

    private void updateArrayPrimitive(Field f, String name) throws IllegalAccessException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        Object data = f.get(object);
        int length = Array.getLength(data);
        for (int i = 0; i < length; i++) {
            addArrayPrimitive(jsonArray, data, i);
        }
        json.add(name, jsonArray.build());
    }

    private void updateCollection(Field f, String name) throws IllegalAccessException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        Iterator items = ((Collection) f.get(object)).iterator();
        while (items != null && items.hasNext()) {
            updateCollectionObject(jsonArray, items.next());
        }
        json.add(name, jsonArray.build());
    }

    public String toJson() {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(json.build());
        writer.close();
        return stringWriter.getBuffer().toString();
    }
}
