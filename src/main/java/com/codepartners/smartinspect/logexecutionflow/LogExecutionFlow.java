package com.codepartners.smartinspect.logexecutionflow;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogExecutionFlow {
    String value() default "";
}