/**
 * 
 */
package com.github.jashawn.examples.springcore;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author jashawn
 */
public class Foo<M extends Model<?>> extends Model<String> implements Holder<M> {

	private M model;
	private Map<String, List<Foo<?>>> children;

	@Override
	public void setModel(M model) {
		this.model = model;
	}

	public M getModel() {
		return model;
	}

	public Map<String, List<Foo<?>>> getChildren() {
		return children;
	}

	public void setChildren(Map<String, List<Foo<?>>> children) {
		this.children = children;
	}

}
