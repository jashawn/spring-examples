/**
 * 
 */
package com.github.jashawn.examples.springcore;

import org.junit.Test;
import org.springframework.core.ResolvableType;
import org.springframework.util.ReflectionUtils;

import static org.junit.Assert.*;

/**
 * 
 *
 * @author jashawn
 */
public class ResolvableTypeTest {

	// @Test
	public void testAllMethod() throws Exception {
		ResolvableType resolvableType = ResolvableType.forClass(Foo.class);
		TestUtils.invokePublicMethod(resolvableType);

	}

	@Test
	public void testInterfaces() {
		ResolvableType resolvableType = ResolvableType.forClass(Foo.class);

		assertSame(Holder.class, resolvableType.getInterfaces()[0].resolve());
		assertSame(Model.class, resolvableType.getInterfaces()[0].resolveGeneric(0));
	}

	@Test
	public void testSuper() throws Exception {
		ResolvableType resolvableType = ResolvableType.forClass(Foo.class);

		assertSame(Model.class, resolvableType.getGeneric(0).resolve());
		assertSame(String.class, resolvableType.getSuperType().resolveGeneric(0));

	}

	@Test
	public void testField() {
		ResolvableType resolvableType = ResolvableType.forField(ReflectionUtils.findField(Foo.class, "children"));

		assertSame(Foo.class, resolvableType.getGeneric(1, 0).resolve());

		assertNull(resolvableType.getGeneric(1, 0, 1).resolve());
	}

}
