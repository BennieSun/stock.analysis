package com.dao.common;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public abstract class JdbcHelper {

    private static Map<Class<?>, BeanPropertyRowMapper<?>> cached = new HashMap<Class<?>, BeanPropertyRowMapper<?>>();
    private static Map<Class<?>, List<String>> cachedColumn2Property = new HashMap<Class<?>, List<String>>();
    private static Map<Class<?>, String> tablenames = new HashMap<Class<?>, String>();

    @SuppressWarnings("unchecked")
    public static <T> RowMapper<T> getRowMapper(Class<T> mappedClass) {
        BeanPropertyRowMapper<?> bprm = cached.get(mappedClass);
        if (bprm == null) {
            bprm = BeanPropertyRowMapper.newInstance(mappedClass);
            cached.put(mappedClass, bprm);
        }
        return (BeanPropertyRowMapper<T>) bprm;
    }

    public static SqlParameterSource getSqlParameterSource(Object bean) {
        return new BeanPropertySqlParameterSource(bean);
    }
    
    public static String getTablename(Class<?> entityClass) {
        String tablename = tablenames.get(entityClass);
        if (tablename == null) {
            tablename = underscoreName(entityClass.getSimpleName());
            tablenames.put(entityClass, tablename);
        }
        return tablename;
    }

    private static String underscoreName(String name) {
        StringBuilder result = new StringBuilder("t_");
        if (name != null && name.length() > 0) {
            result.append(name.substring(0, 1).toLowerCase());
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals(s.toUpperCase())) {
                    result.append("_");
                    result.append(s.toLowerCase());
                } else {
                    result.append(s);
                }
            }
        }
        result.append("_2013");
        return result.toString();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<String> column2properties(Class clazz) {
    	List<String> args = cachedColumn2Property.get(clazz);
        if (args == null) {
            args = new ArrayList<String>();
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(BeanUtils
                    .instantiate(clazz));
            String propertyName;
            for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
                propertyName = pd.getName();
                if (!propertyName.equals("class")) {
                    args.add(propertyName);
                }
            }
            cachedColumn2Property.put(clazz, args);
        }
        return args;
    }
}
