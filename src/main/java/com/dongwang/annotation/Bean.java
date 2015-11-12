package com.dongwang.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Dong Wang.
 * Created on 15/11/11 12:55
 * <p>
 * mark bean can be instance from bean factory
 * and this bean is singleton instance
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Bean {
    String value() default DEFAULT;

    String DEFAULT = "annotation_bean_default";//will use class name or field name
}
