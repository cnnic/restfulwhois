package com.cnnic.whois.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ReflectionUtils {

	/**
	 * 
	 * @param bean
	 * @param propertyName
	 * @return
	 */
	public static Object getPropertyValue(Object bean, String propertyName) {
		PropertyDescriptor descriptor = getPropertyDescriptor(bean.getClass(),
				propertyName);
		if (descriptor == null) {
			throw new RuntimeException(new NoSuchMethodException(
					"Specified property '" + propertyName + "' on '"
							+ bean.getClass() + "' could not found."));
		}

		Method readMethod = descriptor.getReadMethod();
		if (readMethod == null) {
			throw new RuntimeException(new NoSuchMethodException("Property '"
					+ propertyName + "' has no getter method in class '"
					+ bean.getClass() + "'"));
		}

		return invokeMethod(bean, readMethod);
	}

	/**
	 * 
	 * @param bean
	 * @param method
	 * @param arguments
	 * @return
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
	 * 
	 * @param beanClass
	 * @return
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
	 * 
	 * @param beanClass
	 * @param propertyName
	 * @return
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
