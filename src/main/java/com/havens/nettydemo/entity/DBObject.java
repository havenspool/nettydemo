package com.havens.nettydemo.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.google.common.collect.ImmutableMap;
import com.havens.nettydemo.db.DBObjectManager;
import org.json.JSONObject;

/**
 * Created by havens on 15-8-10.
 */
public class DBObject extends JSONObject implements Serializable{

    public String getTableName(){
        Class clazz = getClass();
        String tname = DBObjectManager.getTableNameByObject(clazz);
        if (tname == null) {
            try {
                Field field = clazz.getField("table_name");
                while (field == null && clazz.getSuperclass() != null) {
                    clazz = clazz.getSuperclass();
                    field = clazz.getField("table_name");
                }
                if (field == null) {
                    tname = clazz.getSimpleName() + "s";
                } else
                    tname = (String) field.get(this);
            } catch (Exception e) {
                tname = clazz.getSimpleName() + "s";
            }
            DBObjectManager.setTableNameByClass(clazz, tname);
        }
        return tname;
    }

    public String toString() {
        return __getValueToString();
    }

    private String __getValueToString() {
        StringBuilder sb = new StringBuilder();
        try {
            Map<String, Field> allFields = getAllFields();
            for (Field field : allFields.values()) {
                Class type = field.getType();
                if (Boolean.TYPE == type || Integer.TYPE == type
                        || Long.TYPE == type || Double.TYPE == type
                        || type.equals(String.class)) {
                    String name = field.getName();
                    Object o = "";
                    try {
                        o = field.get(this);
                        if (o == null)
                            o = "";
                    } catch (Exception ignored) {
                    }
                    sb.append(name).append(",").append(o).append(";");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    private Map<String, Field> _allFields = null;
    public Map<String, Field> getAllFields() {
        if (_allFields == null) {
            _allFields = getClazzField(this.getClass());
        }
        return _allFields;
    }

    private static Map<Class, Map<String, Field>> ObjectFieldCache = new ConcurrentHashMap<Class, Map<String, Field>>();
    public static Map<String, Field> getClazzField(final Class clazz) {
        Map<String, Field> result = ObjectFieldCache.get(clazz);
        if (result == null) {
            registerTableClassField(clazz);
            result = ObjectFieldCache.get(clazz);
        }
        return result;
    }
    private static void registerTableClassField(Class clazz) {
        if (ObjectFieldCache.get(clazz) == null) {
            ImmutableMap.Builder<String, Field> classMap = ImmutableMap.builder();
            Class uper = clazz;
            while (uper != null) {
                for (Field field : uper.getDeclaredFields()) {
                    if (Modifier.isFinal(field.getModifiers())
                            || Modifier.isStatic(field.getModifiers())
                            || !Modifier.isPublic(field.getModifiers())
                            || field.getType().isArray())
                        continue;
                    classMap.put(field.getName(), field);
                }
                uper = uper.getSuperclass();
            }
            ObjectFieldCache.put(clazz, classMap.build());
        }
    }

    public Object getValueByField(String name) {
        Field field = getAllFields().get(name);
        if (field != null) {
            try {
                return field.get(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
