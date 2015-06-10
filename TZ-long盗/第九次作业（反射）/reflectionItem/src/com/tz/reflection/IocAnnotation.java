package com.tz.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//作用在属性上面
@Target(ElementType.FIELD)
//存在时间
@Retention(RetentionPolicy.RUNTIME)
public @interface IocAnnotation {
	//传入的值
	int value();
}