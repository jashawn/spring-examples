/**
 * 
 */
package com.github.jashawn.examples.springcore;

/**
 * 
 *
 * @author jashawn
 */
public interface Holder<M extends Model<?>> {

	void setModel(M model);

	M getModel();
}
