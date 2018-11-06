package com.w.annotationcompiler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangpanfeng@nationsky.com on 18/10/24.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface PageRoute {
    String route();
}
