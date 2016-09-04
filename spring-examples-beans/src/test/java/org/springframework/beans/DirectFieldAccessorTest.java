/**
 * 
 */
package org.springframework.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.convert.support.GenericConversionService;

import com.github.jashawn.examples.springbeans.StrMapConverter;
import com.github.jashawn.examples.springbeans.StringToMapPropertyEditor;

import static org.junit.Assert.*;

/**
 * 
 *
 * @author jashawn
 */
public class DirectFieldAccessorTest {

	@Test
	public void testGetPropertyValue() {
		Foo grand = new Foo("Grand");
		Foo kim = new Foo("Kim");
		Foo lily = new Foo("Lily");
		Foo shawn = new Foo("Shawn");
		Foo mike = new Foo("Mike");

		Foo foo = new Foo("Tom");
		foo.setChildren(new LinkedList<>());
		foo.getChildren().add(kim);
		foo.getChildren().add(lily);
		foo.setParent(grand);
		foo.setAttributes(new HashMap<>());
		foo.getAttributes().put("a", "v1");
		foo.getAttributes().put("array", Arrays.asList("a", "b"));
		foo.getAttributes().put("friends", Arrays.asList(shawn, mike));

		DirectFieldAccessor accessor = new DirectFieldAccessor(foo);

		assertSame(grand, accessor.getPropertyValue("parent"));
		assertEquals("Grand", accessor.getPropertyValue("parent.name"));

		assertSame(lily, accessor.getPropertyValue("children[1]"));
		assertEquals("Kim", accessor.getPropertyValue("children[0].name"));

		assertSame(shawn, accessor.getPropertyValue("attributes[friends][0]"));

	}

	@Test
	public void testSetPropertyValue() {
		Foo kim = new Foo("Kim");
		Foo lily = new Foo("Lily");

		Foo foo = new Foo();

		DirectFieldAccessor accessor = new DirectFieldAccessor(foo);

		accessor.setPropertyValue("age", "45");
		assertEquals(45, foo.getAge());

		accessor.setPropertyValue("parent", "Grand");
		assertNotNull(foo.getParent());

		accessor.setAutoGrowNestedPaths(true);
		foo.setParent(null);
		accessor.setPropertyValue("parent.name", "Grand");

		accessor.setPropertyValue(new PropertyValue("children", kim));
		accessor.setPropertyValue(new PropertyValue("children[1]", lily));

		assertTrue(foo.getChildren().size() == 2);
		assertSame(kim, foo.getChildren().get(0));
		assertSame(lily, foo.getChildren().get(1));

	}

	@Test
	public void testSetPropertyValueAutoConvert() {
		Foo foo = new Foo();

		DirectFieldAccessor accessor = new DirectFieldAccessor(foo);
		accessor.setPropertyValue(new PropertyValue("name", "Foo"));
		accessor.setPropertyValue(new PropertyValue("age", "25"));

		assertEquals(foo.getAge(), 25);
		assertEquals(foo.getName(), "Foo");
	}

	@Test
	public void testSetFooProperties() {
		Foo foo = new Foo();

		DirectFieldAccessor accessor = new DirectFieldAccessor(foo);
		accessor.registerCustomEditor(Map.class, new StringToMapPropertyEditor());
		accessor.setPropertyValue(new PropertyValue("properties", "a^v1|b^v2"));
		Map<String, String> map = foo.getProperties();

		validateMap(map);
	}

	@Test
	public void testSetFooProperties_converter() {
		Foo foo = new Foo();

		GenericConversionService conversionService = new GenericConversionService();
		conversionService.addConverter(new StrMapConverter());

		DirectFieldAccessor accessor = new DirectFieldAccessor(foo);
		accessor.setConversionService(conversionService);
		accessor.setPropertyValue(new PropertyValue("properties", "a^v1|b^v2"));
		Map<String, String> map = foo.getProperties();

		validateMap(map);
	}

	@Test
	public void testSetAttributesProp() {
		Foo foo = new Foo();

		DirectFieldAccessor accessor = new DirectFieldAccessor(foo);
		accessor.registerCustomEditor(Map.class, "attributes[prop]", new StringToMapPropertyEditor());
		accessor.setAutoGrowNestedPaths(true);
		accessor.setPropertyValue(new PropertyValue("attributes[prop]", "a^v1|b^v2"));

		@SuppressWarnings("unchecked")
		Map<String, String> prop = (Map<String, String>) foo.getAttributes().get("prop");
		validateMap(prop);
	}

	
	
	private void validateMap(Map<String, String> map) {
		assertTrue(map.size() == 2);
		Map<String, String> ab = new HashMap<>();
		ab.put("a", "v1");
		ab.put("b", "v2");

		assertEquals(ab, map);
	}

}
