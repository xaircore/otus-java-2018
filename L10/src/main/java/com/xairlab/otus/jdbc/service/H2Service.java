package com.xairlab.otus.jdbc.service;

import com.xairlab.otus.jdbc.api.Id;
import com.xairlab.otus.jdbc.connection.DBConnection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class H2Service<T> implements StoreService<T> {

    private final Connection connection;

    public H2Service(DBConnection connection) {
        this.connection = connection.getConnection();
        try {
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getGetterName(String field) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static String getSetterName(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private Field getIdField(Class<T> tClass) {
        Field id = null;
        for (Field field : tClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                id = field;
            }
        }
        if (id == null) {
            throw new RuntimeException(tClass.getName() + " must have field with Id annotation");
        }
        if (id.getType() != long.class) {
            throw new RuntimeException("Field " + id.getName() + " must have " + long.class.getName() + " type");
        }
        return id;
    }

    private long getId(Field f, T data) {
        return invokeGetLong(f, data);
    }


    private List<Field> getFields(Class<T> tClass) {
        ArrayList<Field> fields = new ArrayList<>();
        for (Field field : tClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                fields.add(field);
            }
        }
        return fields;
    }

    private long invokeGetLong(Field f, Object data) {
        String methodName = H2Service.getGetterName(f.getName());
        try {
            Method m = data.getClass().getDeclaredMethod(methodName);
            return (long) m.invoke(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't invoke get long");
        }
    }

    private String invokeGetString(Field f, Object data) {
        String methodName = H2Service.getGetterName(f.getName());
        try {
            Method m = data.getClass().getDeclaredMethod(methodName);
            return (String) m.invoke(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't invoke get String");
        }
    }

    private int invokeGetInt(Field f, Object data) {
        String methodName = H2Service.getGetterName(f.getName());
        try {
            Method m = data.getClass().getDeclaredMethod(methodName);
            return (int) m.invoke(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't invoke get int");
        }
    }

    private void invokeSetLong(Field f, Object data, long value) {
        String methodName = H2Service.getSetterName(f.getName());
        try {
            Method m = data.getClass().getDeclaredMethod(methodName, long.class);
            m.invoke(data, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't invoke set long");
        }
    }

    private void invokeSetString(Field f, Object data, String value) {
        String methodName = H2Service.getSetterName(f.getName());
        try {
            Method m = data.getClass().getDeclaredMethod(methodName, String.class);
            m.invoke(data, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't invoke set String");
        }
    }

    private void invokeSetInt(Field f, Object data, int value) {
        String methodName = H2Service.getSetterName(f.getName());
        try {
            Method m = data.getClass().getDeclaredMethod(methodName, int.class);
            m.invoke(data, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Can't invoke set int");
        }
    }

    private int createStm(int start, PreparedStatement pst, List<Field> fields, T data) throws SQLException {
        int nextIndex = start;
        for (Field f : fields) {
            if (f.getType() == String.class) {
                pst.setString(nextIndex, invokeGetString(f, data));
            } else if (f.getType() == int.class) {
                pst.setInt(nextIndex, invokeGetInt(f, data));
            }
            nextIndex++;
        }
        return nextIndex;
    }

    private void setStm(Field idField, ResultSet rs, List<Field> fields, Object instance) throws SQLException {
        long idData = rs.getLong(1);
        invokeSetLong(idField, instance, idData);
        int start = 2;
        for (Field f : fields) {
            if (f.getType() == String.class) {
                String data = rs.getString(start);
                invokeSetString(f, instance, data);
            } else if (f.getType() == int.class) {
                int data = rs.getInt(start);
                invokeSetInt(f, instance, data);
            }
            start++;
        }
    }

    @Override
    public void save(T data) {
        Class<T> aClass = (Class<T>) data.getClass();
        Field idF = getIdField(aClass);
        long id = getId(idF, data);
        T stub = load(id, aClass);
        List<Field> fields = getFields(aClass);
        if (stub == null) {
            List<String> names = fields.stream().map(field -> field.getName()).collect(Collectors.toList());
            List<String> placeholders = fields.stream().map(field -> "?").collect(Collectors.toList());

            StringBuilder insert = new StringBuilder("insert into " + data.getClass().getSimpleName() + "(id, ");
            insert.append(String.join(", ", names));
            insert.append(") values(?, ");
            insert.append(String.join(", ", placeholders));
            insert.append(")");
            try (PreparedStatement pst = connection.prepareStatement(insert.toString())) {
                pst.setLong(1, id);
                createStm(2, pst, fields, data);
                pst.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            List<String> names = fields.stream().map(field -> field.getName() + " = ?").collect(Collectors.toList());
            StringBuilder update = new StringBuilder("update " + data.getClass().getSimpleName() + " set ");
            update.append(String.join(", ", names));
            update.append("where id = ?");
            try (PreparedStatement pst = connection.prepareStatement(update.toString())) {
                int nextIndex = createStm(1, pst, fields, data);
                pst.setLong(nextIndex, id);
                pst.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T load(long id, Class<T> clazz) {
        try (PreparedStatement pst = connection.prepareStatement("select id, name, age from " + clazz.getSimpleName() + " where id = ?")) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                try {
                    T instance = clazz.getConstructor().newInstance();
                    Field idF = getIdField(clazz);
                    List<Field> fields = getFields(clazz);
                    setStm(idF, rs, fields, instance);
                    return instance;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
