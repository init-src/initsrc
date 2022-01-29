package com.initsrc.common.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 *
 * @author INITSRC (启源)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 部门表的别名
     */
    String deptAlias() default "";
}
