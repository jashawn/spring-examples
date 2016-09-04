/**
 * 
 */
package com.github.jashawn.examples.springbeans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 *
 * @author jashawn
 */
public final class StringMapUtils {

	private StringMapUtils() {

	}

	public static final <V> Map<String, V> toMap(String source, Function<String, V> convert) {

		return toMap(source, '|', '^', convert);
	}

	public static final <V> Map<String, V> toMap(String source, char pairSplit, char kvSplit,
			Function<String, V> convert) {

		String[] kvs = StringUtils.split(source, pairSplit);

		return Arrays.stream(kvs).collect(HashMap<String, V>::new, (map, kv) -> {
			String[] parts = StringUtils.split(kv, kvSplit);
			map.put(parts[0], convert.apply(parts[1]));
		}, (m1, m2) -> {
			m1.putAll(m2);
		});
	}

	public static final <V> String toString(Map<String, V> values, Function<V, String> toString) {

		Set<String> kvs = new HashSet<>();
		values.forEach((k, v) -> {
			kvs.add(k + "^" + toString.apply(v));
		});

		return StringUtils.join(kvs, '|');
	}
}
