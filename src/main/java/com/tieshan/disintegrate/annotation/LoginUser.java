package com.tieshan.disintegrate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/11 15:12
 * @description：登录用户信息
 * @modified By：
 * @version: 1.0.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
