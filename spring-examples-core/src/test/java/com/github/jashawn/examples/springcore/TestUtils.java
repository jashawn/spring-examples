/**
 * 
 */
package com.github.jashawn.examples.springcore;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.ReflectionUtils.MethodFilter;

/**
 * 
 *
 * @author jashawn
 */
public class TestUtils {

	static void invokePublicMethod(Object obj) {
		ReflectionUtils.doWithMethods(obj.getClass(), new MethodCallback() {

			@Override
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
				try {
					System.out.println(method.getName() + ": " + TestUtils.getResultAsString(obj, method));
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}

		}, new MethodFilter() {

			@Override
			public boolean matches(Method method) {
				return method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())
						&& method.getReturnType() != void.class && !"toString".equals(method.getName())
						&& !"hashCode".equals(method.getName());
			}

		});
	}

	private static Object getResultAsString(Object obj, Method method)
			throws IllegalAccessException, InvocationTargetException {
		Object result = method.invoke(obj);
		if (result != null && result.getClass().isArray()) {
			Object[] array = (Object[]) result;
			return "[" + StringUtils.join(array, ",") + "]";
		}
		return result;
	}
}
