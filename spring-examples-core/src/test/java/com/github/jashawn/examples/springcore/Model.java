/**
 * 
 */
package com.github.jashawn.examples.springcore;

/**
 * 
 *
 * @author jashawn
 */
public abstract class Model<T> {

	private T id;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

}
