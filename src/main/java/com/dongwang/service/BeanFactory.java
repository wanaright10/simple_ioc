package com.dongwang.service;


import com.dongwang.annotation.Bean;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dong Wang.
 * Created on 15/11/11 12:59
 * <p>
 * if use ioc,All beans must be get from here
 */
public final class BeanFactory {
    private BeanFactory() {
    }

    private static Map<String, Object> beanPool = new ConcurrentHashMap<>();
    private static final Class<Bean> annotation = Bean.class;

    public static <T> T getBean(@NonNull String beanName, Class<T> clazz) {
        T bean = ((T) beanPool.get(beanName));
        if (bean == null) {
            if (clazz.isAnnotationPresent(annotation)) {
                Bean classAnnotation = clazz.getDeclaredAnnotation(annotation);
                String declaredClassName = classAnnotation.value();
                if (declaredClassName.equals(Bean.DEFAULT)) {
                    declaredClassName = clazz.getSimpleName();
                }

                if (declaredClassName.equals(beanName)) {
                    try {
                        bean = clazz.newInstance();

                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            if (field.isAnnotationPresent(annotation)) {
                                field.setAccessible(true);
                                Bean fieldAnnotation = field.getAnnotation(annotation);
                                String declaredTypeName = fieldAnnotation.value();
                                if (declaredTypeName.equals(Bean.DEFAULT)) {
                                    declaredTypeName = field.getType().getSimpleName();
                                }

                                field.set(bean, getBean(declaredTypeName, field.getType()));
                            }
                        }

                        beanPool.put(beanName, bean);
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return bean;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz.getSimpleName(), clazz);
    }

    public static void addBean(@NonNull String beanName, @NonNull Object bean) {
        beanPool.put(beanName, bean);
    }
}
