/**
 * 
 */
package com.github.jashawn.examples.spring.logging;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 
 *
 * @author jashawn
 */
public class LogFormatter {

	private static final ConcurrentReferenceHashMap<String, LogFormatter> FORMATTERAS = new ConcurrentReferenceHashMap<>();

	private List<Object> sequences;

	private LogFormatter(List<Object> sequences) {
		super();
		this.sequences = sequences;
	}

	public String format(Object... objs) {

		if (CollectionUtils.isEmpty(sequences)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object object : sequences) {
			if (object.getClass() == Integer.class) {
				int index = (int) object;
				if (index < objs.length) {
					sb.append(objs[index].toString());
				}
				continue;
			}
			sb.append(object);
		}
		return sb.toString();
	}

	public static String log(String template, Object... objs) {
		if (StringUtils.isEmpty(template)) {
			return "";
		}
		if (ObjectUtils.isEmpty(objs)) {
			return template;
		}
		LogFormatter formatter = compileTemplateIfNecessary(template);
		return formatter.format(objs);
	}

	private static LogFormatter compileTemplateIfNecessary(String template) {
		if (FORMATTERAS.containsKey(template)) {
			return FORMATTERAS.get(template);
		}

		LogFormatter formatter = compile(template);
		LogFormatter exists = FORMATTERAS.putIfAbsent(template, formatter);
		if (exists == null) {
			return formatter;
		}
		return exists;
	}

	public static LogFormatter compile(String template) {
		char[] str = template.toCharArray();
		boolean placeholder = false;
		int match = 0;
		int index = 0;
		int s = 0;
		List<Object> sequence = new LinkedList<>();
		for (int i = 0, ch = -1; i < str.length; i++) {
			ch = str[i];
			if (ch == '{') {
				placeholder = true;
				match++;
				continue;
			}
			if (placeholder) {
				if (ch >= 48 && ch <= 57) {
					index = index * 10 + (ch - 48);
					continue;
				}
				if (ch == '}') {
					if ('{' == str[i - 1]) {
						placeholder = false;
						index = 0;
						match = 0;
						continue;
					}
					match--;
					if (match >= 0) {
						int offset = i - 1 - (index > 99 ? 3 : (index > 9 ? 2 : 1));
						char[] temp = new char[offset - s];
						System.arraycopy(str, s, temp, 0, temp.length);
						sequence.add(new String(temp));
						sequence.add(index);
						placeholder = false;
						index = 0;
						match = 0;
						s = i + 1;
						continue;
					}
				}
				placeholder = false;
				index = 0;
				match = 0;
				continue;
			}
		}
		if (s != str.length) {
			char[] temp = new char[str.length - s];
			System.arraycopy(str, s, temp, 0, temp.length);
			sequence.add(new String(temp));
		}
		return new LogFormatter(sequence);
	}

}
