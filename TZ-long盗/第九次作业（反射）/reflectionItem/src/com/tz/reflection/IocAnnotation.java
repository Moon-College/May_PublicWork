package com.tz.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//��������������
@Target(ElementType.FIELD)
//����ʱ��
@Retention(RetentionPolicy.RUNTIME)
public @interface IocAnnotation {
	//�����ֵ
	int value();
}