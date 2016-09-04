/**
 * 
 */
package com.github.jashawn.examples.springbeans;

import java.beans.PropertyEditorSupport;

/**
 * 
 *
 * @author jashawn
 */
public class StringToMapPropertyEditor extends PropertyEditorSupport {

	private char pairSeperator = '|';
	private char kvSeperator = '^';

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(StringMapUtils.toMap(text, pairSeperator, kvSeperator, String::toString));

	}

}
