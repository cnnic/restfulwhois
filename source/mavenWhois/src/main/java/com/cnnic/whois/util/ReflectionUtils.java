package com.cnnic.whois.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ReflectionUtils {

	/**
	 * 获取指定bean上的指定属性的值.
	 * 
	 * @param bean
	 *            指定bean
	 * @param propertyName
	 *            指定属性
	 * @return 执行指定bean上属性的getter方法后的返回值
	 */
	public static Object getPropertyValue(Object bean, String propertyName) {
		// 获取属性描述
		PropertyDescriptor descriptor = getPropertyDescriptor(bean.getClass(),
				propertyName);
		if (descriptor == null) {
			throw new RuntimeException(new NoSuchMethodException(
					"Specified property '" + propertyName + "' on '"
							+ bean.getClass() + "' could not found."));
		}

		// 获取getter方法
		Method readMethod = descriptor.getReadMethod();
		if (readMethod == null) {
			throw new RuntimeException(new NoSuchMethodException("Property '"
					+ propertyName + "' has no getter method in class '"
					+ bean.getClass() + "'"));
		}

		// 执行getter方法并返回值
		return invokeMethod(bean, readMethod);
	}

	/**
	 * 执行一个方法.
	 * 
	 * @param bean
	 *            方法所在bean
	 * @param method
	 *            方法
	 * @param arguments
	 *            参数
	 * @return 方法执行后的返回值
	 */
	public static Object invokeMethod(Object bean, Method method,
			Object... arguments) {
		try {
			return method.invoke(bean, arguments);
		} catch (IllegalArgumentException ex) {
			throw new RuntimeException(ex);
		} catch (IllegalAccessException ex) {
			throw new RuntimeException(ex);
		} catch (InvocationTargetException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 获取指定类上的PropertyDescriptor数组.
	 * 
	 * @param beanClass
	 *            类型
	 * @return PropertyDescriptor数组，不会为null
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass) {
		if (beanClass == null) {
			throw new IllegalArgumentException(
					"Arguments must not be null, beanClass: " + beanClass);
		}
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(beanClass);
		} catch (IntrospectionException e) {
			return (new PropertyDescriptor[0]);
		}
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		return (descriptors == null) ? new PropertyDescriptor[0] : descriptors;
	}

	/**
	 * 获取指定类上的PropertyDescriptor.
	 * 
	 * @param beanClass
	 *            类型
	 * @param propertyName
	 *            属性名称
	 * @return 如果没有找到指定属性返回null，否则返回指定属性的值PropertyDescriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor(Class<?> beanClass,
			String propertyName) {
		if (beanClass == null || propertyName == null) {
			throw new IllegalArgumentException(
					"Arguments must not be null, beanClass: '" + beanClass
							+ "', propertyName: '" + propertyName + "'");
		}
		PropertyDescriptor[] descriptors = getPropertyDescriptors(beanClass);
		for (int i = 0; i < descriptors.length; i++) {
			if (propertyName.equals(descriptors[i].getName())) {
				return (descriptors[i]);
			}
		}
		return null;
	}

}
