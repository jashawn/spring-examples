/**
 * 
 */
package com.github.jashawn.examples.springbeans;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 *
 * @author jashawn
 */
public class StrMapConverter implements Converter<String, Map<String, String>> {

	private char pairSeperator = '|';
	private char kvSeperator = '^';

	public StrMapConverter() {
		super();
	}

	public StrMapConverter(char pairSeperator, char kvSeperator) {
		super();
		this.pairSeperator = pairSeperator;
		this.kvSeperator = kvSeperator;
	}

	@Override
	public Map<String, String> convert(String source) {
		return StringMapUtils.toMap(source, pairSeperator, kvSeperator, String::toString);
	}

}