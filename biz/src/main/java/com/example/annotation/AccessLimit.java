package com.example.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {
    /**
     * 每秒允许访问次数
     * @return
     */
    int value() default 10;
}
