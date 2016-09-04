/**
 * 
 */
package org.springframework.beans;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jashawn
 */
public class Foo {

	private int age = 35;
	private String name;
	private Foo parent;
	private List<Foo> children;
	private Map<String, Object> attributes;
	private Map<String, String> properties;

	public Foo() {
		super();
	}

	public Foo(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Foo getParent() {
		return parent;
	}

	public void setParent(Foo parent) {
		this.parent = parent;
	}

	public List<Foo> getChildren() {
		return children;
	}

	public void setChildren(List<Foo> children) {
		this.children = children;
	}

	public int getAge() {
		return age;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Foo [age=" + age + ", name=" + name + ", parent=" + parent + ", children=" + children + ", attributes="
				+ attributes + "]";
	}

}
