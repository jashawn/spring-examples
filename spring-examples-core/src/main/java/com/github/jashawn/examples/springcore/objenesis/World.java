/**
 * 
 */
package com.github.jashawn.examples.springcore.objenesis;

import java.util.UUID;

/**
 * 
 *
 * @author jashawn
 */
public class World {

	private String name;
	private String id;

	public World() {
		id = UUID.randomUUID().toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return id + "/" + name;
	}
}
