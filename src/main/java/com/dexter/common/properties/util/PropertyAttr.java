package com.dexter.common.properties.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by likun on 2015/12/26.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface PropertyAttr {
    /**
     * 键名
     *
     * @return
     */
    public abstract String key();

    /**
     * 默认键值
     * @return
     */
    public abstract String defalutValue() default "";

}
