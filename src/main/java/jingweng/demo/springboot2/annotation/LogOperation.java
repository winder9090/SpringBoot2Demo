package jingweng.demo.springboot2.annotation;

import java.lang.annotation.*;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.annotation
 * @className: LogOperation
 * @author:
 * @description: 操作日志注解
 * @date: 2023/3/21 13:50
 * @version: 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogOperation {

    String value() default "";
}
