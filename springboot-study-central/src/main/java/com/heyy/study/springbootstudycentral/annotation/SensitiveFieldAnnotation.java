package com.heyy.study.springbootstudycentral.annotation;

import java.lang.annotation.*;

/**
 * @Classname SensitiveFieldAnnotation
 * @Description TODO
 * @Date 2019/5/10 19:55
 * @Created by Breeze
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveFieldAnnotation {
}
